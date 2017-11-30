package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.User;
import com.gongsibao.er.base.IUserService;

@Service
public class UserService extends PersistableService<User> implements IUserService {

    public UserService(){
        super();
        this.type=User.class;
    }
}