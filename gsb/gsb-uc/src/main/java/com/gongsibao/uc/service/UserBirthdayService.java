package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.UserBirthday;
import com.gongsibao.uc.base.IUserBirthdayService;

@Service
public class UserBirthdayService extends PersistableService<UserBirthday> implements IUserBirthdayService {

    public UserBirthdayService(){
        super();
        this.type=UserBirthday.class;
    }
}