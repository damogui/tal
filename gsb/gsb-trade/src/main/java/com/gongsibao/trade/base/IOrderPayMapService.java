package com.gongsibao.trade.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.OrderPayMap;

public interface IOrderPayMapService extends IPersistableService<OrderPayMap> {
	
	List<OrderPayMap> queryByOrderId(Integer orderId);
}