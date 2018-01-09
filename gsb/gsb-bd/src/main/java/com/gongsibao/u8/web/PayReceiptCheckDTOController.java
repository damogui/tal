package com.gongsibao.u8.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.dic.PayReceiptStatus;
import com.gongsibao.u8.base.IPayService;

public class PayReceiptCheckDTOController extends ListPart {

	// 绑定回单编号
	public void bindReceiptNo(int payId, String receiptNo) {
		IPayService payService = ServiceFactory.create(IPayService.class);
		// Pay pay =payService.byId(payId);//未能解析实体关系路径：/orderPayMaps/accountId
		Pay pay = payService.getById(payId);
		pay.setReceiptNo(receiptNo);
		payService.save(pay);
	}

	// 改变回单状态
	public Boolean changeReceiptStatus(int payId, PayReceiptStatus receiptStatus) {
		IPayService payService = ServiceFactory.create(IPayService.class);
		return payService.changeReceiptStatus(payId, receiptStatus);
	}

}
