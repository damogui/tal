package com.gongsibao.cw.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.cw.base.IPaymentService;
import com.gongsibao.entity.cw.Payment;

public class PaymentBillFormPart extends FormPart{
	
	IPaymentService paymentService = ServiceFactory.create(IPaymentService.class);
	
	/**
	 * 保存付款申请数据
	* @Title: saveLoan  
	* @Description: TODO
	* @param @param loan
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	public Boolean savePayment(Payment payment) {
		
		return paymentService.savePayment(payment);
	}

}
