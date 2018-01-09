package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.ICustomerLogService;
import com.gongsibao.entity.crm.CustomerLog;

@Service
public class CustomerLogService extends PersistableService<CustomerLog> implements ICustomerLogService {

    public CustomerLogService(){
        super();
        this.type=CustomerLog.class;
    }
}