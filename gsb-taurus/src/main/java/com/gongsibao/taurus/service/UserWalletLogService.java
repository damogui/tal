package com.gongsibao.taurus.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.taurus.UserWalletLog;
import com.gongsibao.taurus.base.IUserWalletLogService;

@Service
public class UserWalletLogService extends PersistableService<UserWalletLog> implements IUserWalletLogService {

    public UserWalletLogService(){
        super();
        this.type=UserWalletLog.class;
    }


}
