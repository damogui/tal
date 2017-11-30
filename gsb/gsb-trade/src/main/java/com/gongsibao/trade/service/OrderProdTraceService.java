package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.OrderProdTrace;
import com.gongsibao.trade.base.IOrderProdTraceService;

@Service
public class OrderProdTraceService extends PersistableService<OrderProdTrace> implements IOrderProdTraceService {

    public OrderProdTraceService(){
        super();
        this.type=OrderProdTrace.class;
    }
}