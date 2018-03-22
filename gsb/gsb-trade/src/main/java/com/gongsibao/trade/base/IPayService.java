package com.gongsibao.trade.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.entity.trade.Pay;

public interface IPayService extends IPersistableService<Pay> {
	
	@Transaction
	Boolean applyPay(Pay pay);
}