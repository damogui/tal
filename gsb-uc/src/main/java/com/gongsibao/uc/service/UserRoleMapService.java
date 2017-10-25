package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.UserRoleMap;
import com.gongsibao.uc.base.IUserRoleMapService;

@Service
public class UserRoleMapService extends PersistableService<UserRoleMap> implements IUserRoleMapService {

    public UserRoleMapService(){
        super();
        this.type=UserRoleMap.class;
    }
}