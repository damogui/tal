package com.gongsibao.cw.service.action.loan;

import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.authorization.UserPermission;
import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Organization;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.wx.ea.base.IEaMessageService;

import com.gongsibao.cw.base.IAuditRecordService;
import com.gongsibao.cw.base.IEmployeeService;
import com.gongsibao.entity.cw.AuditRecord;
import com.gongsibao.entity.cw.Loan;
import com.gongsibao.entity.cw.dict.FinanceDict;

public class ActionLoanApplyAudit  implements IAction{

	//审核记录服务
	IAuditRecordService auditRecordService = ServiceFactory.create(IAuditRecordService.class);
	//获取上级领导
	IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class); 
	
	IEaMessageService eMessageService = ServiceFactory.create(IEaMessageService.class);
	
	@Override
	public void execute(ActionContext ctx) {
		 Loan loan = (Loan) ctx.getItem();
		 if(loan != null && loan.getId() != null){
			 UserPermission up = UserPermissionManager.getUserPermission();
			 List<Employee> leaderList  = this.getEmployeeList(up.getEmployee().getDepartmentId());
			 if(leaderList != null && leaderList.size() >0){
				 for(Employee employee : leaderList){
					 saveAudit(loan,up,employee);
				 }
			 }else{
				 //主岗位
				 Organization org = up.getEmployee().getPost();
				 if(org != null && org.getPositionId().intValue() == 4 ){
					 throw new BusinessException("您当前的组织机构错误，请联系管理员。");
				 }else{
					 Employee financeEmployee = employeeService.getEmployeeByFinanceLeader(FinanceDict.WX_MSG_CODE);
					 saveAudit(loan,up,financeEmployee);
				 }
				
			 }
		 }
	}
	
	/**
	 * 保存审批信息
	 * @param loan
	 * @param up
	 * @param employee
	 */
	private void saveAudit ( Loan loan ,UserPermission up,Employee employee){
		 //保存创建人上级主管审核信息
	   	 AuditRecord au = new AuditRecord();
	   	 au.toNew();
	   	 au.setAuditUserId(employee.getId());    //获取上级主管id
	   	 au.setFormType(FinanceDict.FormType.JKD);
	   	 au.setFormId(loan.getId());
	   	 au.setApplyDepartmentId(up.getEmployee().getDepartmentId());
	   	 au.setApplyUserId(SessionManager.getUserId());
	   	 au.setStatus(FinanceDict.AuditDetailStatus.WAIT); //待审核
	   	 auditRecordService.save(au);
		 String  content = "【财务报销】"+loan.getCreator()+"提交了借款申请，单据编号："+loan.getCode()+" 请您尽快处理。";
	   	 eMessageService.send(FinanceDict.WX_MSG_CODE, content, employee.getLoginName());
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
				if(userId == employee.getId()){
					result = false;
				}
			}
		}else{
			result = false;
		}
		return result;
	}
}
