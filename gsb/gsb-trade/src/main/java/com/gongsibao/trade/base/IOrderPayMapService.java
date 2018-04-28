package com.gongsibao.trade.base;

import java.util.List;

import com.gongsibao.entity.trade.dic.AuditStatusType;
import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.OrderPayMap;
import org.netsharp.core.annotations.Transaction;

public interface IOrderPayMapService extends IPersistableService<OrderPayMap> {

    List<OrderPayMap> queryByOrderId(Integer orderId);

    /*根据支付id获取payMap*/
    List<OrderPayMap> queryByPayId(Integer payId);

}