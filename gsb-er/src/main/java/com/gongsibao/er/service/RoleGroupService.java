package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.RoleGroup;
import com.gongsibao.er.base.IRoleGroupService;

@Service
public class RoleGroupService extends PersistableService<RoleGroup> implements IRoleGroupService {

    public RoleGroupService(){
        super();
        this.type=RoleGroup.class;
    }
}