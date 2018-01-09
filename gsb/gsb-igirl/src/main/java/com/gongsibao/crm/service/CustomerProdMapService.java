package com.gongsibao.crm.service;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.crm.base.ICustomerProdMapService;
import com.gongsibao.entity.crm.CustomerProdMap;

@Service
public class CustomerProdMapService extends GsbPersistableService<CustomerProdMap> implements ICustomerProdMapService {

    public CustomerProdMapService(){
        super();
        this.type=CustomerProdMap.class;
    }
}