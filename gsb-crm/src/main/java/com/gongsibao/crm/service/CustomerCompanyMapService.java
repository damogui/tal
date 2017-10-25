package com.gongsibao.crm.service;

import org.netsharp.communication.Service;

import com.gongsibao.crm.base.ICustomerCompanyMapService;
import com.gongsibao.entity.crm.CustomerCompanyMap;

@Service
public class CustomerCompanyMapService extends GsbPersistableService<CustomerCompanyMap> implements ICustomerCompanyMapService {

    public CustomerCompanyMapService(){
        super();
        this.type=CustomerCompanyMap.class;
    }
}