package com.gongsibao.cw.web;

import java.sql.Types;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.wx.pa.dic.FansStatus;

import com.gongsibao.cw.base.IAuditRecordService;
import com.gongsibao.cw.base.IExpenseService;
import com.gongsibao.cw.base.ILoanService;
import com.gongsibao.cw.base.IPaymentService;
import com.gongsibao.entity.cw.AuditRecord;
import com.gongsibao.entity.cw.Expense;
import com.gongsibao.entity.cw.Loan;
import com.gongsibao.entity.cw.Payment;
import com.gongsibao.entity.cw.dict.FinanceDict;

public class AuditBillFormController {
    //借款服务
	ILoanService loanService = ServiceFactory.create(ILoanService.class);
	//报销服务
	IExpenseService expenseService = ServiceFactory.create(IExpenseService.class);
	//付款服务
	IPaymentService paymentService = ServiceFactory.create(IPaymentService.class);
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
		Oql oql = new Oql();
		if (formType == 1) { // 查询借款单据
			oql.setType(Loan.class);
			oql.setSelects("loan.*,loan.costDetailItem.*,loan.files.*,loan.auditItem.*,loan.setOfBooks.name");
			oql.setFilter("id=?");
			oql.getParameters().add("id", formId, Types.INTEGER);
			Loan entity = loanService.queryFirst(oql);
			t = (T) entity;
		}
		if (formType == 2) { //查询报销单据
			oql.setType(Expense.class);
			oql.setSelects("*");
			oql.setFilter("id=?");
			oql.getParameters().add("id", formId, Types.INTEGER);
			Expense entity = expenseService.queryFirst(oql);
			t = (T) entity;
		}
		if (formType == 3) { //查询付款单据
			oql.setType(Payment.class);
			oql.setSelects("*");
			oql.setFilter("id=?");
			oql.getParameters().add("id", formId, Types.INTEGER);
			Payment entity = paymentService.queryFirst(oql);
			t = (T) entity;
		}
		return t;
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
	public Boolean saveAudit(AuditRecord auditRecord,Integer formId){
		return auditRecordService.saveAudit(auditRecord);
	}
}
