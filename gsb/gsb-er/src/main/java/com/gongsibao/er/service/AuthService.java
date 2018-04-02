package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.Auth;
import com.gongsibao.er.base.IAuthService;

@Service
public class AuthService extends PersistableService<Auth> implements IAuthService {

    public AuthService(){
        super();
        this.type=Auth.class; 
    }
}