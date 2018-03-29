package com.gongsibao.cw.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.cw.base.ILoanService;
import com.gongsibao.entity.cw.Loan;

public class LoansBillFormPart extends FormPart{
	
	ILoanService loanService = ServiceFactory.create(ILoanService.class);
	
	/**
	 * 保存借款申请数据
	* @Title: saveLoan  
	* @Description: TODO
	* @param @param loan
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	public Boolean saveLoan(Loan loan) {
		
		return loanService.saveLoan(loan);
	}

}
