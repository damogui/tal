package com.gongsibao.trade.base;

import com.gongsibao.entity.trade.OrderCooperation;
import org.netsharp.base.IPersistableService;

import java.util.List;

public interface IOrderCooperationService extends IPersistableService<OrderCooperation> {

    List<OrderCooperation> getByOrderId(Integer orderId);

}
