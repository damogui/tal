package com.gongsibao.trade.base;

import java.util.Map;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.Invoice;

public interface IInvoiceService extends IPersistableService<Invoice> {
	
	/**
	 * 申请发票
 	 * @Title: applyInvoice  
	 * @Description: TODO
	 * @param @return    参数  
	 * @return Boolean    返回类型  
	 * @throws
	 */
	public Boolean applyInvoice(Invoice invoice,Map<String,Object> paraMap);
}