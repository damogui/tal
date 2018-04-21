package com.gongsibao.trade.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.OrderDiscount;
import org.netsharp.core.annotations.Transaction;

public interface IOrderDiscountService extends IPersistableService<OrderDiscount> {
	
	List<OrderDiscount> queryByOrderId(Integer orderId);

	/**
	 * 更新订单优惠折扣
	 * @param pkid
	 * @param no
	 * @return
	 */
	@Transaction
	int updateNo(Integer pkid,String no);
}