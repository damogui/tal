package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.OrderLog;
import com.gongsibao.er.base.IOrderLogService;

@Service
public class OrderLogService extends PersistableService<OrderLog> implements IOrderLogService {

    public OrderLogService(){
        super();
        this.type=OrderLog.class;
    }
}