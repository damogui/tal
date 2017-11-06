package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.CostInvoice;
import com.gongsibao.trade.base.ICostInvoiceService;

@Service
public class CostInvoiceService extends PersistableService<CostInvoice> implements ICostInvoiceService {

    public CostInvoiceService(){
        super();
        this.type=CostInvoice.class;
    }
}