package com.gongsibao.cw.base;

import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.entity.cw.AuditRecord;
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
	public  Loan  getBillByFormId(Integer formId);
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
	/**
	 * 获取登陆用户为还清借款单据
	* @Title: getLoanByUserId  
	* @Description: TODO
	* @param @param userId
	* @param @return    参数  
	* @return List<Loan>    返回类型  
	* @throws
	 */
	public List<Loan> getLoanByUserId(Integer userId);
}
