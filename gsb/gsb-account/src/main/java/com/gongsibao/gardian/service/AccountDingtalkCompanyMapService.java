package com.gongsibao.gardian.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.acount.AccountDingtalkCompanyMap;
import com.gongsibao.gardian.base.IAccountDingtalkCompanyMapService;

@Service
public class AccountDingtalkCompanyMapService extends PersistableService<AccountDingtalkCompanyMap> implements IAccountDingtalkCompanyMapService {

    public AccountDingtalkCompanyMapService(){
        super();
        this.type=AccountDingtalkCompanyMap.class;
    }
}