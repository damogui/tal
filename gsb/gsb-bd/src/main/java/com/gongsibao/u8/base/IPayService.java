package com.gongsibao.u8.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.dic.PayReceiptStatus;

public interface IPayService extends IPersistableService<Pay> {

	Boolean changeReceiptStatus(int payId,PayReceiptStatus receiptStatus);
}
