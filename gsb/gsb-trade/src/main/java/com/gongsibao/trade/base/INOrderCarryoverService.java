package com.gongsibao.trade.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.NOrderCarryover;

public interface INOrderCarryoverService extends IPersistableService<NOrderCarryover>{

	/**
	 * 根据来源订单Id获取结转集合
	 * @param orderId
	 * @return
	 */
	NOrderCarryover queryByFormOrderId(Integer orderId);
}
