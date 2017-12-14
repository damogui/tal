package com.gongsibao.u8.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.entity.trade.Pay;
import com.gongsibao.u8.base.IPayService;

public class PayReceiptCheckDTOController extends ListPart{

	//绑定回单编号
	public void bindReceiptNo(int id,String receiptNo){
		//IPayReceiptCheckDTOService reportService = ServiceFactory.create(IPayReceiptCheckDTOService.class);
		//reportService.save(entity)
		
		IPayService payService=  ServiceFactory.create(IPayService.class);		
		Pay pay =payService.byId(id);
		pay.setReceiptNo(receiptNo);		
		payService.save(pay);
		
		
	}
	
}
