package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.OrderTaskItem;
import com.gongsibao.er.base.IOrderTaskItemService;

@Service
public class OrderTaskItemService extends PersistableService<OrderTaskItem> implements IOrderTaskItemService {

    public OrderTaskItemService(){
        super();
        this.type=OrderTaskItem.class;
    }
}