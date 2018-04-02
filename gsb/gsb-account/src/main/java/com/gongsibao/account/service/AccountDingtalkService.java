package com.gongsibao.account.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.account.base.IAccountDingtalkService;
import com.gongsibao.entity.acount.AccountDingtalk;

@Service
public class AccountDingtalkService extends PersistableService<AccountDingtalk> implements IAccountDingtalkService {

    public AccountDingtalkService(){
        super();
        this.type=AccountDingtalk.class;
    }
}