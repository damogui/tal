package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.ICustomerContactViewService;
import com.gongsibao.entity.crm.CustomerContactView;

@Service
public class CustomerContactViewService extends PersistableService<CustomerContactView> implements ICustomerContactViewService {

    public CustomerContactViewService(){
        super();
        this.type=CustomerContactView.class;
    }
}