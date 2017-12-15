package com.gongsibao.u8.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.entity.trade.dic.OrderManualVoucherStatus;
import com.gongsibao.u8.base.ISoOrderService;

public class ManualVoucherOrderDTOController extends ListPart {

	// 改变是否手动状态
	public Boolean changeManualVoucherStatus(int orderId, OrderManualVoucherStatus manuaVoucherStatus) {
		ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);
		return orderService.updateManuaVoucherStatus(orderId, manuaVoucherStatus);
	}
}