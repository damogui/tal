package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.UserRoleMap;
import com.gongsibao.er.base.IUserRoleMapService;

@Service
public class UserRoleMapService extends PersistableService<UserRoleMap> implements IUserRoleMapService {

    public UserRoleMapService(){
        super();
        this.type=UserRoleMap.class;
    }
}