package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.CustomerMessage;
import com.gongsibao.er.base.ICustomerMessageService;

@Service
public class CustomerMessageService extends PersistableService<CustomerMessage> implements ICustomerMessageService {

    public CustomerMessageService(){
        super();
        this.type=CustomerMessage.class;
    }
}