package com.gongsibao.account.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.account.base.IAccountDingtalkCompanyMapService;
import com.gongsibao.entity.acount.AccountDingtalkCompanyMap;

@Service
public class AccountDingtalkCompanyMapService extends PersistableService<AccountDingtalkCompanyMap> implements IAccountDingtalkCompanyMapService {

    public AccountDingtalkCompanyMapService(){
        super();
        this.type=AccountDingtalkCompanyMap.class;
    }
}