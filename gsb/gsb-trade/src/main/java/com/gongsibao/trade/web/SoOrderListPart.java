package com.gongsibao.trade.web;

import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.entity.trade.OrderProdTrace;
import com.gongsibao.trade.base.IOrderProdTraceService;

public class SoOrderListPart extends ListPart {
	IOrderProdTraceService prodTraceService = ServiceFactory.create(IOrderProdTraceService.class);

	public List<OrderProdTrace> querySoOrderTraceList(Integer soOrderId) {
		List<OrderProdTrace> orderTraceList = prodTraceService.querySoOrderTraceList(soOrderId);
		return orderTraceList;
	}
}
