package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.ICustomerShareService;
import com.gongsibao.entity.crm.CustomerShare;

@Service
public class CustomerShareService extends PersistableService<CustomerShare> implements ICustomerShareService {

    public CustomerShareService(){
        super();
        this.type=CustomerShare.class;
    }
}