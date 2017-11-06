package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.LoginLog;
import com.gongsibao.uc.base.ILoginLogService;

@Service
public class LoginLogService extends PersistableService<LoginLog> implements ILoginLogService {

    public LoginLogService(){
        super();
        this.type=LoginLog.class;
    }
}