package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.Invoice;
import com.gongsibao.trade.base.IInvoiceService;

@Service
public class InvoiceService extends PersistableService<Invoice> implements IInvoiceService {

    public InvoiceService(){
        super();
        this.type=Invoice.class;
    }
}