package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.CompanyIndustry;
import com.gongsibao.yj.base.ICompanyIndustryService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class CompanyIndustryService extends PersistableService<CompanyIndustry> implements ICompanyIndustryService {

    public CompanyIndustryService(){
        super();
        this.type=CompanyIndustry.class;
    }
}