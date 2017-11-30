package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.OrderDiscount;
import com.gongsibao.trade.base.IOrderDiscountService;

@Service
public class OrderDiscountService extends PersistableService<OrderDiscount> implements IOrderDiscountService {

    public OrderDiscountService(){
        super();
        this.type=OrderDiscount.class;
    }
}