package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.crm.Customer;
import com.gongsibao.er.base.ICustomerService;

@Service
public class CustomerService extends PersistableService<Customer> implements ICustomerService {

    public CustomerService(){
        super();
        this.type=Customer.class;
    }
}