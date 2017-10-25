package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.OrderGroup;
import com.gongsibao.er.base.IOrderGroupService;

@Service
public class OrderGroupService extends PersistableService<OrderGroup> implements IOrderGroupService {

    public OrderGroupService(){
        super();
        this.type=OrderGroup.class;
    }
}