package com.gongsibao.account.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.account.base.IAccountDingtalkKeywordService;
import com.gongsibao.entity.acount.AccountDingtalkKeyword;

@Service
public class AccountDingtalkKeywordService extends PersistableService<AccountDingtalkKeyword> implements IAccountDingtalkKeywordService {

    public AccountDingtalkKeywordService(){
        super();
        this.type=AccountDingtalkKeyword.class;
    }
}