package com.gongsibao.trade.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.OrderDiscount;

public interface IOrderDiscountService extends IPersistableService<OrderDiscount> {
	
	List<OrderDiscount> queryByOrderId(Integer orderId);
}