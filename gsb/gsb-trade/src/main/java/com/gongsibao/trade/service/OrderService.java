package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IOrderService;

@Service
public class OrderService extends PersistableService<SoOrder> implements IOrderService {

    public OrderService(){
        super();
        this.type=SoOrder.class;
    }
}