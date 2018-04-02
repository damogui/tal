package com.gongsibao.gardian.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.acount.AccountDingtalk;
import com.gongsibao.gardian.base.IAccountDingtalkService;

@Service
public class AccountDingtalkService extends PersistableService<AccountDingtalk> implements IAccountDingtalkService {

    public AccountDingtalkService(){
        super();
        this.type=AccountDingtalk.class;
    }
}