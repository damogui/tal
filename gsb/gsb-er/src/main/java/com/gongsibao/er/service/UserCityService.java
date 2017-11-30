package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.UserCity;
import com.gongsibao.er.base.IUserCityService;

@Service
public class UserCityService extends PersistableService<UserCity> implements IUserCityService {

    public UserCityService(){
        super();
        this.type=UserCity.class;
    }
}