package com.gongsibao.product.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.ServiceExt;
import com.gongsibao.product.base.IServiceExtService;

@Service
public class ServiceExtService extends PersistableService<ServiceExt> implements IServiceExtService {

    public ServiceExtService(){
        super();
        this.type=ServiceExt.class;
    }
}