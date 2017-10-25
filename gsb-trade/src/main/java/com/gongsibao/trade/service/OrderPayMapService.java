package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.trade.base.IOrderPayMapService;

@Service
public class OrderPayMapService extends PersistableService<OrderPayMap> implements IOrderPayMapService {

    public OrderPayMapService(){
        super();
        this.type=OrderPayMap.class;
    }
}