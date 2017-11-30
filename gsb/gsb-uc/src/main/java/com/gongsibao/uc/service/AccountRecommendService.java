package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.AccountRecommend;
import com.gongsibao.uc.base.IAccountRecommendService;

@Service
public class AccountRecommendService extends PersistableService<AccountRecommend> implements IAccountRecommendService {

    public AccountRecommendService(){
        super();
        this.type=AccountRecommend.class;
    }
}