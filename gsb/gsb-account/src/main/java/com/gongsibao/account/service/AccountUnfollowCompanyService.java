package com.gongsibao.account.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.account.base.IAccountUnfollowCompanyService;
import com.gongsibao.entity.acount.AccountUnfollowCompany;

@Service
public class AccountUnfollowCompanyService extends PersistableService<AccountUnfollowCompany> implements IAccountUnfollowCompanyService {

    public AccountUnfollowCompanyService(){
        super();
        this.type=AccountUnfollowCompany.class;
    }
}