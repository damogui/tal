package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.Role;
import com.gongsibao.er.base.IRoleService;

@Service
public class RoleService extends PersistableService<Role> implements IRoleService {

    public RoleService(){
        super();
        this.type=Role.class;
    }
}