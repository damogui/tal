package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.CompanyContactinfo;
import com.gongsibao.yj.base.ICompanyContactinfoService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class CompanyContactinfoService extends PersistableService<CompanyContactinfo> implements ICompanyContactinfoService {

    public CompanyContactinfoService(){
        super();
        this.type=CompanyContactinfo.class;
    }
}