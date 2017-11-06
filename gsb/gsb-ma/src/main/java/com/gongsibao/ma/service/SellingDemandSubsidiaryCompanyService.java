package com.gongsibao.ma.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.ma.SellingDemandSubsidiaryCompany;
import com.gongsibao.ma.base.ISellingDemandSubsidiaryCompanyService;

@Service
public class SellingDemandSubsidiaryCompanyService extends PersistableService<SellingDemandSubsidiaryCompany> implements ISellingDemandSubsidiaryCompanyService {

    public SellingDemandSubsidiaryCompanyService(){
        super();
        this.type=SellingDemandSubsidiaryCompany.class;
    }
}