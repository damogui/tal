package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.AccountDingtalkCompany;
import com.gongsibao.uc.base.IAccountDingtalkCompanyService;

@Service
public class AccountDingtalkCompanyService extends PersistableService<AccountDingtalkCompany> implements IAccountDingtalkCompanyService {

    public AccountDingtalkCompanyService(){
        super();
        this.type=AccountDingtalkCompany.class;
    }
}