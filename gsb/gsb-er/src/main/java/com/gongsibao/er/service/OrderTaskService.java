package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.OrderTask;
import com.gongsibao.er.base.IOrderTaskService;

@Service
public class OrderTaskService extends PersistableService<OrderTask> implements IOrderTaskService {

    public OrderTaskService(){
        super();
        this.type=OrderTask.class;
    }
}