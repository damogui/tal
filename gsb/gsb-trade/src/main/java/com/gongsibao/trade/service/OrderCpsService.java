package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.OrderCps;
import com.gongsibao.trade.base.IOrderCpsService;

@Service
public class OrderCpsService extends PersistableService<OrderCps> implements IOrderCpsService {

    public OrderCpsService(){
        super();
        this.type=OrderCps.class;
    }
}