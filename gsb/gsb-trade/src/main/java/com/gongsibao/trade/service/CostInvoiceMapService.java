package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.CostInvoiceMap;
import com.gongsibao.trade.base.ICostInvoiceMapService;

@Service
public class CostInvoiceMapService extends PersistableService<CostInvoiceMap> implements ICostInvoiceMapService {

    public CostInvoiceMapService(){
        super();
        this.type=CostInvoiceMap.class;
    }
}