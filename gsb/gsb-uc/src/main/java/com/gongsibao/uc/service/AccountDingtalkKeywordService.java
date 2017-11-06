package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.AccountDingtalkKeyword;
import com.gongsibao.uc.base.IAccountDingtalkKeywordService;

@Service
public class AccountDingtalkKeywordService extends PersistableService<AccountDingtalkKeyword> implements IAccountDingtalkKeywordService {

    public AccountDingtalkKeywordService(){
        super();
        this.type=AccountDingtalkKeyword.class;
    }
}