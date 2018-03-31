package com.gongsibao.cw.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.entity.cw.Expense;
import com.gongsibao.entity.cw.Loan;


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

}
