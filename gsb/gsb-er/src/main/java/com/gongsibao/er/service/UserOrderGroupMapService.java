package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.UserOrderGroupMap;
import com.gongsibao.er.base.IUserOrderGroupMapService;

@Service
public class UserOrderGroupMapService extends PersistableService<UserOrderGroupMap> implements IUserOrderGroupMapService {

    public UserOrderGroupMapService(){
        super();
        this.type=UserOrderGroupMap.class;
    }
}