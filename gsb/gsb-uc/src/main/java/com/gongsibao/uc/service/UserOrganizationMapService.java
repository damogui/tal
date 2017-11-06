package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.UserOrganizationMap;
import com.gongsibao.uc.base.IUserOrganizationMapService;

@Service
public class UserOrganizationMapService extends PersistableService<UserOrganizationMap> implements IUserOrganizationMapService {

    public UserOrganizationMapService(){
        super();
        this.type=UserOrganizationMap.class;
    }
}