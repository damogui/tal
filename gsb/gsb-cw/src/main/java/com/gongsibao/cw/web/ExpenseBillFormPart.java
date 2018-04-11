package com.gongsibao.cw.web;

import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.cw.base.IExpenseService;
import com.gongsibao.cw.base.ILoanService;
import com.gongsibao.entity.cw.Expense;
import com.gongsibao.entity.cw.Loan;

public class ExpenseBillFormPart extends FormPart{
	
	IExpenseService expenseService = ServiceFactory.create(IExpenseService.class);
	
	ILoanService loanService = ServiceFactory.create(ILoanService.class);
	/**
	 * 打开页面前获取数据
	 */
	 public IPersistable newInstance(Object par) {
        this.getService();
        IPersistable entity = this.service.newInstance();
        Expense expense = (Expense) entity;
        expense.setLoanAmount(getLoanAmount());
        if(expense.getAmount()!=null){
        	expense.setAmount(expense.getAmount()/100);
        }
        return expense;
	 }
	 
	 private Integer getLoanAmount(){
		 Integer loanAmount = 0;
		 Integer userId = SessionManager.getUserId();
		 List<Loan> loanList = loanService.getLoanByUserId(userId);
		 if(loanList != null && loanList.size() >0){
			for (Loan loan : loanList) {
				loanAmount += loan.getArrearsAmount();
			}
		 }
		 return loanAmount;
	 }
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
