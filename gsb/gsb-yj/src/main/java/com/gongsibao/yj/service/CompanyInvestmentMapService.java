package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.CompanyInvestmentMap;
import com.gongsibao.yj.base.ICompanyInvestmentMapService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class CompanyInvestmentMapService extends PersistableService<CompanyInvestmentMap> implements ICompanyInvestmentMapService {

    public CompanyInvestmentMapService(){
        super();
        this.type=CompanyInvestmentMap.class;
    }
}