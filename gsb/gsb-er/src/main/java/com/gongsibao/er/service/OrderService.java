package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.Order;
import com.gongsibao.er.base.IOrderService;

@Service
public class OrderService extends PersistableService<Order> implements IOrderService {

    public OrderService(){
        super();
        this.type=Order.class;
    }
}