package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.User;
import com.gongsibao.uc.base.IUserService;

@Service
public class UserService extends PersistableService<User> implements IUserService {

    public UserService(){
        super();
        this.type=User.class;
    }
}