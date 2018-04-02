package com.gongsibao.account.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.account.base.IAccountRecommendService;
import com.gongsibao.entity.acount.AccountRecommend;

@Service
public class AccountRecommendService extends PersistableService<AccountRecommend> implements IAccountRecommendService {

    public AccountRecommendService(){
        super();
        this.type=AccountRecommend.class;
    }
}