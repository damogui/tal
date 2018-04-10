package com.gongsibao.cw.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.entity.cw.AuditRecord;
import com.gongsibao.entity.cw.Expense;
import com.gongsibao.entity.cw.Loan;
import com.gongsibao.entity.cw.Payment;


public interface IExpenseService extends IPersistableService<Expense> {
	
	/**
	 * 保存报销请数据
	* @Title: saveLoan  
	* @Description: TODO
	* @param @param loan
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	 @Transaction
	public Boolean saveExpense (Expense entity);
	 
	 /**
	 * 通过表单id  表单类型获取表单数据
	* @Title: getBillByFormId  
	* @Description: TODO
	* @param @param formId
	* @param @param formType
	* @param @return    参数  
	* @return T    返回类型  
	* @throws
	 */
	public  Expense  getBillByFormId(Integer formId);
	
	/**
	 * 财务办理完成修改订单状态
	* @Title: updateStatus  
	* @Description: TODO
	* @param @param auditRecord
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	public Boolean updateStatus(AuditRecord auditRecord);

}
