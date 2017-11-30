package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.Auth;
import com.gongsibao.uc.base.IAuthService;

@Service
public class AuthService extends PersistableService<Auth> implements IAuthService {

    public AuthService(){
        super();
        this.type=Auth.class;
    }
}