package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.uc.User;
import com.gongsibao.trade.base.IUcUserService;

@Service
public class UcUserService extends PersistableService<User> implements IUcUserService {

    public UcUserService(){
        super();
        this.type=User.class;
    }
}