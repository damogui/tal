package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.OrderProdCost;
import com.gongsibao.trade.base.IOrderProdCostService;

@Service
public class OrderProdCostService extends PersistableService<OrderProdCost> implements IOrderProdCostService {

    public OrderProdCostService(){
        super();
        this.type=OrderProdCost.class;
    }
}