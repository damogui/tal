package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.AccountDingtalkCompanyMap;
import com.gongsibao.uc.base.IAccountDingtalkCompanyMapService;

@Service
public class AccountDingtalkCompanyMapService extends PersistableService<AccountDingtalkCompanyMap> implements IAccountDingtalkCompanyMapService {

    public AccountDingtalkCompanyMapService(){
        super();
        this.type=AccountDingtalkCompanyMap.class;
    }
}