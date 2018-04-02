package com.gongsibao.gardian.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.acount.AccountDingtalkCompany;
import com.gongsibao.gardian.base.IAccountDingtalkCompanyService;

@Service
public class AccountDingtalkCompanyService extends PersistableService<AccountDingtalkCompany> implements IAccountDingtalkCompanyService {

    public AccountDingtalkCompanyService(){
        super();
        this.type=AccountDingtalkCompany.class;
    }
}