package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.UserAuth;
import com.gongsibao.er.base.IUserAuthService;

@Service
public class UserAuthService extends PersistableService<UserAuth> implements IUserAuthService {

    public UserAuthService(){
        super();
        this.type=UserAuth.class;
    }
}