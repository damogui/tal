package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.trade.base.IOrderProdService;

@Service
public class OrderProdService extends PersistableService<OrderProd> implements IOrderProdService {

    public OrderProdService(){
        super();
        this.type=OrderProd.class;
    }
}