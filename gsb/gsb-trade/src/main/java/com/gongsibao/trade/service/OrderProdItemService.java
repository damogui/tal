package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.OrderProdItem;
import com.gongsibao.trade.base.IOrderProdItemService;

@Service
public class OrderProdItemService extends PersistableService<OrderProdItem> implements IOrderProdItemService {

    public OrderProdItemService(){
        super();
        this.type=OrderProdItem.class;
    }
}