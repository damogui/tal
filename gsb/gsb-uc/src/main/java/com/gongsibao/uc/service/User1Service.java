package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.User1;
import com.gongsibao.uc.base.IUser1Service;

@Service
public class User1Service extends PersistableService<User1> implements IUser1Service {

    public User1Service(){
        super();
        this.type=User1.class;
    }
}