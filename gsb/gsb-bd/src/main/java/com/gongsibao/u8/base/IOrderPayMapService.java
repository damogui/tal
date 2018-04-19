package com.gongsibao.u8.base;

import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.OrderManualVoucherStatus;
import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import java.util.List;
import java.util.Map;

public interface IOrderPayMapService extends IPersistableService<OrderPayMap> {
    List<OrderPayMap> queryByOrderIdPayId(Integer orderId, Integer payId);
}
