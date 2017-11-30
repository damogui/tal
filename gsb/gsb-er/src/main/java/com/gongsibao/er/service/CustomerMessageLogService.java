package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.CustomerMessageLog;
import com.gongsibao.er.base.ICustomerMessageLogService;

@Service
public class CustomerMessageLogService extends PersistableService<CustomerMessageLog> implements ICustomerMessageLogService {

    public CustomerMessageLogService(){
        super();
        this.type=CustomerMessageLog.class;
    }
}