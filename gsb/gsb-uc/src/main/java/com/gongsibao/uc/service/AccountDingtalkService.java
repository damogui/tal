package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.AccountDingtalk;
import com.gongsibao.uc.base.IAccountDingtalkService;

@Service
public class AccountDingtalkService extends PersistableService<AccountDingtalk> implements IAccountDingtalkService {

    public AccountDingtalkService(){
        super();
        this.type=AccountDingtalk.class;
    }
}