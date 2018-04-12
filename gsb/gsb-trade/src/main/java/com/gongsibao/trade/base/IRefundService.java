package com.gongsibao.trade.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.Refund;

public interface IRefundService extends IPersistableService<Refund> {
	
	List<Refund> queryByOrderId(Integer orderId);
	/**
	 * 根据退款主键获取退款实体
	 * @param id
	 * @return
	 */
	Refund queryById(Integer id);
}