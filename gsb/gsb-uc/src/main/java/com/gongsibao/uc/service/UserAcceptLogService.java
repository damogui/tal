package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.UserAcceptLog;
import com.gongsibao.uc.base.IUserAcceptLogService;

@Service
public class UserAcceptLogService extends PersistableService<UserAcceptLog> implements IUserAcceptLogService {

    public UserAcceptLogService(){
        super();
        this.type=UserAcceptLog.class;
    }
}