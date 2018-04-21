package com.gongsibao.cw.service.action.audit;

import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.authorization.UserPermission;
import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;
import org.netsharp.wx.ea.base.IEaMessageService;

import com.gongsibao.cw.base.IAuditRecordService;
import com.gongsibao.cw.base.IEmployeeService;
import com.gongsibao.cw.base.IExpenseService;
import com.gongsibao.cw.base.ILoanService;
import com.gongsibao.cw.base.IPaymentService;
import com.gongsibao.entity.cw.Allocation;
import com.gongsibao.entity.cw.AuditRecord;
import com.gongsibao.entity.cw.Expense;
import com.gongsibao.entity.cw.Loan;
import com.gongsibao.entity.cw.Payment;
import com.gongsibao.entity.cw.dict.FinanceDict;

public class ActionAuditRecordAudit  implements IAction{

	
	//审核记录服务
	IAuditRecordService auditRecordService = ServiceFactory.create(IAuditRecordService.class);
	//获取上级领导
	IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class); 
			
	IEaMessageService eMessageService = ServiceFactory.create(IEaMessageService.class);
	
	@Override
	public void execute(ActionContext ctx) {
		AuditRecord auditRecord = (AuditRecord) ctx.getItem();
		 if(auditRecord != null && auditRecord.getId() != null){
			 UserPermission up = UserPermissionManager.getUserPermission();
			 //审核通过
			 if(auditRecord.getStatus().getValue().intValue()== FinanceDict.AuditDetailStatus.AGREE.getValue().intValue()){
				 //当审核人为财务主管 通过将状态给为财务办理
				 Employee financeEmployee = employeeService.getEmployeeByFinanceLeader(FinanceDict.WX_MSG_CODE);
				 if(financeEmployee != null && SessionManager.getUserId().intValue() == financeEmployee.getId().intValue()){
					 updateBillStatus(auditRecord.getFormId(),auditRecord.getFormType().getValue(),FinanceDict.AuditStatus.Status_4.getValue());
				 }else{
					 List<Employee> leaderList  = this.getEmployeeList(up.getEmployee().getDepartmentId());
					 if(leaderList != null && leaderList.size() >0){
						 for(Employee employee : leaderList){
							 saveAudit(employee,auditRecord);
						 }
					 }else{
						 //没有上级领导 财务主管审批 （郝舰）
						 Employee  employee = new Employee();
						 employee.setId(financeEmployee.getId()); //根据线上数据修改成郝总的 id  
						 saveAudit(employee,auditRecord);
					 }
				 }
			 }else{  //驳回
				 //删除上一次审核记录 将单据状态修改为被驳回
				 updateBillStatus(auditRecord.getFormId(),auditRecord.getFormType().getValue(),FinanceDict.AuditStatus.Status_6.getValue());
				 auditRecordService.deleteAuditByFormId(auditRecord.getFormId(),auditRecord.getFormType().getValue());
			 }
		 }
	}
	
	
	private void saveAudit( Employee employee,AuditRecord auditRecord){
		//保存创建人上级主管审核信息
	   	 AuditRecord au = new AuditRecord();
	   	 au.toNew();
	   	 au.setAuditUserId(employee.getId());    //获取上级主管id
	   	 au.setFormType(auditRecord.getFormType());
	   	 au.setFormId(auditRecord.getFormId());
	   	 au.setApplyUserId(auditRecord.getApplyUserId());
	   	 au.setApplyDepartmentId(auditRecord.getApplyDepartmentId());
	   	 au.setStatus(FinanceDict.AuditDetailStatus.WAIT); //待审核
	   	 auditRecordService.save(au);
	   	 sendWxMessage(employee,auditRecord);
	}
	//微信发送消息
	private void sendWxMessage(Employee employee,AuditRecord auditRecord) {
		 String  content = "【财务报销】";
	   	 if(auditRecord.getFormType().getValue() == FinanceDict.FormType.JKD.getValue()){
	   		 ILoanService loanService =   ServiceFactory.create(ILoanService.class);
	   		  Loan loan =  loanService.byId(auditRecord.getFormId());
	   		 content += loan.getCreator()+"提交了借款申请，单据编号："+loan.getCode()+" 请您尽快处理。";
	   	 }
	   	 
	   	 if(auditRecord.getFormType().getValue() == FinanceDict.FormType.BXD.getValue()){
	   		 IExpenseService expenseService =   ServiceFactory.create(IExpenseService.class);
	   		 Expense expense =  expenseService.byId(auditRecord.getFormId());
	   		 content += expense.getCreator()+"提交了报销申请，单据编号："+expense.getCode()+" 请您尽快处理。";
	   	 }
	   	 
	   	 if(auditRecord.getFormType().getValue() == FinanceDict.FormType.FKD.getValue()){
	   		 IPaymentService paymentService =   ServiceFactory.create(IPaymentService.class);
	   		 Payment payment =  paymentService.byId(auditRecord.getFormId());
	   		 content += payment.getCreator()+"提交了付款申请，单据编号："+payment.getCode()+" 请您尽快处理。";
	   	 }
	   	 eMessageService.send(FinanceDict.WX_MSG_CODE, content, employee.getMobile());
	}
	/**
	 * 修改单据状态  财务办理状态
	* @Title: updateBillStatus  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param formId    参数  
	* @return void    返回类型  
	* @throws
	 */
	private void updateBillStatus(Integer formId ,Integer formType,Integer auditStatus){
		
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		String cmdText = "";
		if(formType == FinanceDict.FormType.JKD.getValue()){
			updateSql.update("cw_loan");
			updateSql.set("status", auditStatus);
			updateSql.where("id =" + formId);
		    cmdText = updateSql.toSQL();
			IPersister<Loan> pm = PersisterFactory.create();
			pm.executeNonQuery(cmdText, null);
		}else if(formType == FinanceDict.FormType.BXD.getValue()){
			updateSql.update("cw_expense");
			updateSql.set("status", auditStatus);
			updateSql.where("id =" + formId);
		    cmdText = updateSql.toSQL();
			IPersister<Expense> pm = PersisterFactory.create();
			pm.executeNonQuery(cmdText, null);
		} else if(formType == FinanceDict.FormType.FKD.getValue()){
			updateSql.update("cw_payment");
			updateSql.set("status", auditStatus);
			updateSql.where("id =" + formId);
		    cmdText = updateSql.toSQL();
			IPersister<Payment> pm = PersisterFactory.create();
			pm.executeNonQuery(cmdText, null);
		}else if(formType == FinanceDict.FormType.DBD.getValue()){
			updateSql.update("cw_allocation");
			updateSql.set("status", auditStatus);
			updateSql.where("id =" + formId);
		    cmdText = updateSql.toSQL();
			IPersister<Allocation> pm = PersisterFactory.create();
			pm.executeNonQuery(cmdText, null);
		}
	}
	
	/**
	 * 获取所有上级主管
	* @Title: getEmployeeList  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @return    参数  
	* @return List<Employee>    返回类型  
	* @throws
	 */
	private List<Employee>  getEmployeeList(Integer departmentId){
		List<Employee> leaderList = employeeService.getEmployeeByLeader(departmentId);
		if(isEmployee(leaderList)){
			return leaderList;
		}else{
			return employeeService.getEmployeeByParentLeader(departmentId);
		}
		
	}
	/**
	 * 判断登录人和主管领导是否同一人
	* @Title: isEmployee  
	* @Description: TODO
	* @param @param list
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	public Boolean isEmployee(List<Employee> list){
		Boolean result = true;
		if(list != null && list.size()>0){
			Integer userId = SessionManager.getUserId();
			for(Employee employee : list){
				if(userId.intValue() == employee.getId().intValue()){
					result = false;
				}
			}
		}else{
			result = false;
		}
		return result;
	}

}
