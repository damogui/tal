package com.gongsibao.u8.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.OrderManualVoucherStatus;

public interface ISoOrderService extends IPersistableService<SoOrder> {

	Boolean updateManuaVoucherStatus(Integer orderId,OrderManualVoucherStatus status);

}
