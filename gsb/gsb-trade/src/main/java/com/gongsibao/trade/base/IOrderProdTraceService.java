package com.gongsibao.trade.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.OrderProdTrace;

public interface IOrderProdTraceService extends IPersistableService<OrderProdTrace> {
	//查看办理进度
	List<OrderProdTrace> querySoOrderTraceList(Integer soOrderId);

}