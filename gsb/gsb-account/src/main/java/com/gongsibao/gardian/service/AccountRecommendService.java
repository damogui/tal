package com.gongsibao.gardian.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.acount.AccountRecommend;
import com.gongsibao.gardian.base.IAccountRecommendService;

@Service
public class AccountRecommendService extends PersistableService<AccountRecommend> implements IAccountRecommendService {

    public AccountRecommendService(){
        super();
        this.type=AccountRecommend.class;
    }
}