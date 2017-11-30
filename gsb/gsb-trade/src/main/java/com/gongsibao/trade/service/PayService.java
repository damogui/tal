package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.Pay;
import com.gongsibao.trade.base.IPayService;

@Service
public class PayService extends PersistableService<Pay> implements IPayService {

    public PayService(){
        super();
        this.type=Pay.class;
    }
}