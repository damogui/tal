package com.gongsibao.cw.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.cw.AuditRecord;
import com.gongsibao.entity.cw.Loan;
import com.gongsibao.entity.cw.Payment;


public interface IPaymentService extends IPersistableService<Payment> {

	/**
	 * 付款单数据保存
	* @Title: savePayment  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param payment
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	public Boolean savePayment(Payment payment);
	
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
		public  Payment  getBillByFormId(Integer formId);
		
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
