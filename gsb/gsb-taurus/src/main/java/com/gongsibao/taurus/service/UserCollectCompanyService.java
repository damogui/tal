package com.gongsibao.taurus.service;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.taurus.UserCollectCompany;
import com.gongsibao.taurus.base.IUserCollectCompanyService;

@Service
public class UserCollectCompanyService extends GsbPersistableService<UserCollectCompany> implements IUserCollectCompanyService {

    public UserCollectCompanyService(){
        super();
        this.type=UserCollectCompany.class;
    }


}
