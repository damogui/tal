package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.OrderTaskAuditor;
import com.gongsibao.er.base.IOrderTaskAuditorService;

@Service
public class OrderTaskAuditorService extends PersistableService<OrderTaskAuditor> implements IOrderTaskAuditorService {

    public OrderTaskAuditorService(){
        super();
        this.type=OrderTaskAuditor.class;
    }
}