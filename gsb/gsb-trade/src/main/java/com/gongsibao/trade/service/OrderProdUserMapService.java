package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.OrderProdUserMap;
import com.gongsibao.trade.base.IOrderProdUserMapService;

@Service
public class OrderProdUserMapService extends PersistableService<OrderProdUserMap> implements IOrderProdUserMapService {

    public OrderProdUserMapService(){
        super();
        this.type=OrderProdUserMap.class;
    }
}