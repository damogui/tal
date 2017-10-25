package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.Account;
import com.gongsibao.uc.base.IAccountService;

@Service
public class AccountService extends PersistableService<Account> implements IAccountService {

    public AccountService(){
        super();
        this.type=Account.class;
    }
}