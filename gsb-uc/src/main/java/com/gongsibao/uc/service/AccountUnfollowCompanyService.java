package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.AccountUnfollowCompany;
import com.gongsibao.uc.base.IAccountUnfollowCompanyService;

@Service
public class AccountUnfollowCompanyService extends PersistableService<AccountUnfollowCompany> implements IAccountUnfollowCompanyService {

    public AccountUnfollowCompanyService(){
        super();
        this.type=AccountUnfollowCompany.class;
    }
}