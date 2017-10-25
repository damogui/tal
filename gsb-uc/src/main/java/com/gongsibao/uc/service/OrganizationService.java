package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.Organization;
import com.gongsibao.uc.base.IOrganizationService;

@Service
public class OrganizationService extends PersistableService<Organization> implements IOrganizationService {

    public OrganizationService(){
        super();
        this.type=Organization.class;
    }
}