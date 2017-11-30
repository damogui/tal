package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.UserBusiness;
import com.gongsibao.uc.base.IUserBusinessService;

@Service
public class UserBusinessService extends PersistableService<UserBusiness> implements IUserBusinessService {

    public UserBusinessService(){
        super();
        this.type=UserBusiness.class;
    }
}