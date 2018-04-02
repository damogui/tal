package com.gongsibao.gardian.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.acount.AccountDingtalkKeyword;
import com.gongsibao.gardian.base.IAccountDingtalkKeywordService;

@Service
public class AccountDingtalkKeywordService extends PersistableService<AccountDingtalkKeyword> implements IAccountDingtalkKeywordService {

    public AccountDingtalkKeywordService(){
        super();
        this.type=AccountDingtalkKeyword.class;
    }
}