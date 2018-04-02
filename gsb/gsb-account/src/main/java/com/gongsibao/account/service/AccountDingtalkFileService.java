package com.gongsibao.account.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.account.base.IAccountDingtalkFileService;
import com.gongsibao.entity.acount.AccountDingtalkFile;

@Service
public class AccountDingtalkFileService extends PersistableService<AccountDingtalkFile> implements IAccountDingtalkFileService {

    public AccountDingtalkFileService(){
        super();
        this.type=AccountDingtalkFile.class;
    }
}