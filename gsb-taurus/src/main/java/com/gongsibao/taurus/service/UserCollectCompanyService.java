package com.gongsibao.taurus.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.taurus.UserCollectCompany;
import com.gongsibao.taurus.base.IUserCollectCompanyService;

@Service
public class UserCollectCompanyService extends PersistableService<UserCollectCompany> implements IUserCollectCompanyService {

    public UserCollectCompanyService(){
        super();
        this.type=UserCollectCompany.class;
    }


}
