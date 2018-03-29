package com.gongsibao.trade.base;

import java.util.List;

import com.gongsibao.entity.trade.dic.AuditStatusType;
import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.OrderPayMap;

public interface IOrderPayMapService extends IPersistableService<OrderPayMap> {

    List<OrderPayMap> queryByOrderId(Integer orderId);

    /*根据支付id获取payMap*/
    OrderPayMap queryByPayId(Integer payId);

    /*根据支付id进行回写支付*/
    int updateByPayId(Integer payId);
}