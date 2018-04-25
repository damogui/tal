package com.gongsibao.u8.web;

import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.entity.trade.SoOrderVoucherFollow;
import com.gongsibao.entity.trade.dic.OrderManualVoucherStatus;
import com.gongsibao.trade.base.ISoOrderService;
import com.gongsibao.u8.base.ISoOrderVoucherFollowService;

public class ManualVoucherOrderDTOController extends ListPart {

	// 改变是否手动状态
	public Boolean changeManualVoucherStatus(int orderId, OrderManualVoucherStatus manuaVoucherStatus) {
		ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);
		return orderService.updateManuaVoucherStatus(orderId, manuaVoucherStatus);
	}

	// 添加跟进记录
	public Boolean addOrderVoucherFollowLog(int orderId, String content) {
		ISoOrderVoucherFollowService voucherFollowService = ServiceFactory.create(ISoOrderVoucherFollowService.class);
		return voucherFollowService.addOrderVoucherFollowLog(orderId, content);
	}

	// 获取当前跟进人的跟进记录
	public List<SoOrderVoucherFollow> getOrderVoucherFollowLogByUserId(int orderId) {
		ISoOrderVoucherFollowService voucherFollowService = ServiceFactory.create(ISoOrderVoucherFollowService.class);
		return voucherFollowService.getOrderVoucherFollowLogByUserId(orderId);
	}

}