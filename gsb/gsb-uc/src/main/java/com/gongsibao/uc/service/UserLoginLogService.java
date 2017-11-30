package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.UserLoginLog;
import com.gongsibao.uc.base.IUserLoginLogService;

@Service
public class UserLoginLogService extends PersistableService<UserLoginLog> implements IUserLoginLogService {

    public UserLoginLogService(){
        super();
        this.type=UserLoginLog.class;
    }
}