package com.gongsibao.trade.web;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.entity.trade.Invoice;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IInvoiceService;
import com.gongsibao.u8.base.ISoOrderService;

public class InvoiceFormPart extends FormPart{

	IInvoiceService invoiceService = ServiceFactory.create(IInvoiceService.class); 
	

	/**
	 * 订单id 查询订单信息
	* @Title: querySoOrderById  
	* @Description: TODO
	* @param @param orderId
	* @param @return    参数  
	* @return SoOrder    返回类型  
	* @throws
	 */
	public SoOrder querySoOrderById(Integer orderId){
		ISoOrderService soOrderService = ServiceFactory.create(ISoOrderService.class);
		SoOrder soOrder = soOrderService.byId(orderId);
		return soOrder;
	}
	/**
	 * 申请发票
	* @Title: applyInvoice  
	* @Description: TODO 
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	public Boolean applyInvoice(Invoice invoice,Integer orderId){
		Map<String,Object> paraMap = new HashMap<String,Object >();
		paraMap.put("orderId", orderId);
		return invoiceService.applyInvoice(invoice,paraMap) ;
	}
	

}
	