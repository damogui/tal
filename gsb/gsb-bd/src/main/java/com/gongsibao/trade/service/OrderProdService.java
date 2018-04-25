package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.trade.base.IOrderProdService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import java.util.List;
import java.util.Map;

@Service
public class OrderProdService extends PersistableService<OrderProd> implements IOrderProdService {
    @Override
    public List<OrderProd> findOrderProdByOrderIds(List<Integer> orderIds) {
        return null;
    }

    @Override
    public Map<Integer, Integer> findPayablePrice(List<Integer> prodIds) {
        return null;
    }
}
