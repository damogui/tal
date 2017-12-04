package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.CompanyContactWebsite;
import com.gongsibao.yj.base.ICompanyContactWebsiteService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class CompanyContactWebsiteService extends PersistableService<CompanyContactWebsite> implements ICompanyContactWebsiteService {

    public CompanyContactWebsiteService(){
        super();
        this.type=CompanyContactWebsite.class;
    }
}