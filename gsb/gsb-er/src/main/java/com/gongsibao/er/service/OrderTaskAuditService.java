package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.OrderTaskAudit;
import com.gongsibao.er.base.IOrderTaskAuditService;

@Service
public class OrderTaskAuditService extends PersistableService<OrderTaskAudit> implements IOrderTaskAuditService {

    public OrderTaskAuditService(){
        super();
        this.type=OrderTaskAudit.class;
    }
}