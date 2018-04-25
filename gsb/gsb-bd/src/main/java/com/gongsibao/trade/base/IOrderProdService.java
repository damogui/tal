package com.gongsibao.trade.base;

import com.gongsibao.entity.trade.OrderProd;
import org.netsharp.base.IPersistableService;

import java.util.List;
import java.util.Map;

public interface IOrderProdService extends IPersistableService<OrderProd> {

    List<OrderProd> findOrderProdByOrderIds(List<Integer> orderIds);

    Map<Integer, Integer> findPayablePrice(List<Integer> prodIds);
}
