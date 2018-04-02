package com.gongsibao.gardian.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.acount.AccountDingtalkFile;
import com.gongsibao.gardian.base.IAccountDingtalkFileService;

@Service
public class AccountDingtalkFileService extends PersistableService<AccountDingtalkFile> implements IAccountDingtalkFileService {

    public AccountDingtalkFileService(){
        super();
        this.type=AccountDingtalkFile.class;
    }
}