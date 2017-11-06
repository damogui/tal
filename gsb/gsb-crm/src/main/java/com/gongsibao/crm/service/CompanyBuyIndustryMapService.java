package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.ICompanyBuyIndustryMapService;
import com.gongsibao.entity.crm.CompanyBuyIndustryMap;

@Service
public class CompanyBuyIndustryMapService extends PersistableService<CompanyBuyIndustryMap> implements ICompanyBuyIndustryMapService {

    public CompanyBuyIndustryMapService(){
        super();
        this.type=CompanyBuyIndustryMap.class;
    }
}