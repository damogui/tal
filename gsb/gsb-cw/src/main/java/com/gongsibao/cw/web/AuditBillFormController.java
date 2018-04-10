package com.gongsibao.cw.web;

import org.netsharp.communication.ServiceFactory;

import com.gongsibao.cw.base.IAllocationService;
import com.gongsibao.cw.base.IAuditRecordService;
import com.gongsibao.cw.base.IExpenseService;
import com.gongsibao.cw.base.ILoanService;
import com.gongsibao.cw.base.IPaymentService;
import com.gongsibao.cw.service.ExpenseService;
import com.gongsibao.entity.cw.AuditRecord;
import com.gongsibao.entity.cw.dict.FinanceDict;

public class AuditBillFormController {
    //借款服务
	ILoanService loanService = ServiceFactory.create(ILoanService.class);
	//报销服务
	IExpenseService expenseService = ServiceFactory.create(IExpenseService.class);
	//付款服务
	IPaymentService paymentService = ServiceFactory.create(IPaymentService.class);
	//调拨服务
	IAllocationService allocationService = ServiceFactory.create(IAllocationService.class);
	//审批记录服务
	IAuditRecordService auditRecordService = ServiceFactory.create(IAuditRecordService.class);

	/**
	 * 单据id查询单据信息
	 * 
	 * @Title: getBillByFormId
	 * @Description: TODO 后期优化
	 * @param @param formId
	 * @param @param formType
	 * @param @return 参数
	 * @return T 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public <T> T getBillByFormId(Integer formId, Integer formType) {
		T t = null;
		if(formType == FinanceDict.FormType.JKD.getValue()){ //借款单
			t = (T)loanService.getBillByFormId(formId);
		} else if(formType == FinanceDict.FormType.BXD.getValue()){ //报销单
			t = (T)expenseService.getBillByFormId(formId);
		}else if (formType == FinanceDict.FormType.FKD.getValue()){ //付款单
			t = (T)paymentService.getBillByFormId(formId);
		}else if(formType == FinanceDict.FormType.DBD.getValue()){ //调拨单
			t = (T)allocationService.getBillByFormId(formId);
		}
		return t ;
	}
	/**
	 * 保存审核信息
	* @Title: saveAudit  
	* @Description: TODO
	* @param @param auditRecord
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	public Boolean saveAudit(AuditRecord auditRecord){
		return auditRecordService.saveAudit(auditRecord);
	}
	/**
	 * 保存财务办理意见 并修改单据状态
	* @Title: saveFinance  
	* @Description: TODO
	* @param @param auditRecord
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	public Boolean saveFinance(AuditRecord auditRecord){
		Boolean bool = false;
		if(auditRecord.getFormType().getValue() == FinanceDict.FormType.JKD.getValue()){ //借款单
			bool = loanService.updateStatus(auditRecord);
		} else if(auditRecord.getFormType().getValue() == FinanceDict.FormType.BXD.getValue()){ //报销单
			bool = expenseService.updateStatus(auditRecord);
		}else if (auditRecord.getFormType().getValue() == FinanceDict.FormType.FKD.getValue()){ //付款单
			bool = paymentService.updateStatus(auditRecord);
		}else if(auditRecord.getFormType().getValue() == FinanceDict.FormType.DBD.getValue()){ //调拨单
			bool = allocationService.updateStatus(auditRecord);
		}
		return bool;
	}
}
