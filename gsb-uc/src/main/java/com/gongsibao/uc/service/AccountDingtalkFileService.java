package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.AccountDingtalkFile;
import com.gongsibao.uc.base.IAccountDingtalkFileService;

@Service
public class AccountDingtalkFileService extends PersistableService<AccountDingtalkFile> implements IAccountDingtalkFileService {

    public AccountDingtalkFileService(){
        super();
        this.type=AccountDingtalkFile.class;
    }
}