package com.gongsibao.gardian.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.acount.AccountUnfollowCompany;
import com.gongsibao.gardian.base.IAccountUnfollowCompanyService;

@Service
public class AccountUnfollowCompanyService extends PersistableService<AccountUnfollowCompany> implements IAccountUnfollowCompanyService {

    public AccountUnfollowCompanyService(){
        super();
        this.type=AccountUnfollowCompany.class;
    }
}