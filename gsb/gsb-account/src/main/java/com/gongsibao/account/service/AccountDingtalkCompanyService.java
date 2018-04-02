package com.gongsibao.account.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.account.base.IAccountDingtalkCompanyService;
import com.gongsibao.entity.acount.AccountDingtalkCompany;

@Service
public class AccountDingtalkCompanyService extends PersistableService<AccountDingtalkCompany> implements IAccountDingtalkCompanyService {

    public AccountDingtalkCompanyService(){
        super();
        this.type=AccountDingtalkCompany.class;
    }
}