package com.gongsibao.cw.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.entity.cw.Loan;


public interface ILoanService extends IPersistableService<Loan> {
	
	/**
	 * 保存借款申请数据
	* @Title: saveLoan  
	* @Description: TODO
	* @param @param loan
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	 @Transaction
	public Boolean saveLoan (Loan loan);

}
