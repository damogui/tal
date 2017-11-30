package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.OrganizationBdDictMap;
import com.gongsibao.uc.base.IOrganizationBdDictMapService;

@Service
public class OrganizationBdDictMapService extends PersistableService<OrganizationBdDictMap> implements IOrganizationBdDictMapService {

    public OrganizationBdDictMapService(){
        super();
        this.type=OrganizationBdDictMap.class;
    }
}