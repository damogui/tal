package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.OrderProdTraceFile;
import com.gongsibao.trade.base.IOrderProdTraceFileService;

@Service
public class OrderProdTraceFileService extends PersistableService<OrderProdTraceFile> implements IOrderProdTraceFileService {

    public OrderProdTraceFileService(){
        super();
        this.type=OrderProdTraceFile.class;
    }
}