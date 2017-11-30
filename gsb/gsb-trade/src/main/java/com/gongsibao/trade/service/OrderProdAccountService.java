package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.OrderProdAccount;
import com.gongsibao.trade.base.IOrderProdAccountService;

@Service
public class OrderProdAccountService extends PersistableService<OrderProdAccount> implements IOrderProdAccountService {

    public OrderProdAccountService(){
        super();
        this.type=OrderProdAccount.class;
    }
}