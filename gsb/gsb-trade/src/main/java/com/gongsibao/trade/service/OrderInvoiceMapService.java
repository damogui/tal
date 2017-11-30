package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.OrderInvoiceMap;
import com.gongsibao.trade.base.IOrderInvoiceMapService;

@Service
public class OrderInvoiceMapService extends PersistableService<OrderInvoiceMap> implements IOrderInvoiceMapService {

    public OrderInvoiceMapService(){
        super();
        this.type=OrderInvoiceMap.class;
    }
}