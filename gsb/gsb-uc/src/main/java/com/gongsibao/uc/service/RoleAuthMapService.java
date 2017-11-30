package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.RoleAuthMap;
import com.gongsibao.uc.base.IRoleAuthMapService;

@Service
public class RoleAuthMapService extends PersistableService<RoleAuthMap> implements IRoleAuthMapService {

    public RoleAuthMapService(){
        super();
        this.type=RoleAuthMap.class;
    }
}