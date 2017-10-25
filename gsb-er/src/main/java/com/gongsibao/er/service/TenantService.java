package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.Tenant;
import com.gongsibao.er.base.ITenantService;

@Service
public class TenantService extends PersistableService<Tenant> implements ITenantService {

    public TenantService(){
        super();
        this.type=Tenant.class;
    }
}