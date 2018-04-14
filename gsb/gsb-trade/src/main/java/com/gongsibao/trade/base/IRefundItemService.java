package com.gongsibao.trade.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.RefundItem;

public interface IRefundItemService extends IPersistableService<RefundItem> {
	
	public List<RefundItem> queryByRefundId(Integer refundId);
}