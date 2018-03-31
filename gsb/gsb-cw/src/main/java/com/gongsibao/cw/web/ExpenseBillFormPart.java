package com.gongsibao.cw.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.cw.base.IExpenseService;
import com.gongsibao.entity.cw.Expense;

public class ExpenseBillFormPart extends FormPart{
	
	IExpenseService expenseService = ServiceFactory.create(IExpenseService.class);
	
	/**
	 * 保存报销单据数据
	* @Title: saveExpense  
	* @Description: TODO
	* @param @param entity
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	public Boolean saveExpense(Expense entity){
		return expenseService.saveExpense(entity);
	}

}
