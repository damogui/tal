package com.gongsibao.cw.base;

import org.netsharp.base.IPersistableService;

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
}
