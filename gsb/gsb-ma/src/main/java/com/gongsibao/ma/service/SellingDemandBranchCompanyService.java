package com.gongsibao.ma.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.ma.SellingDemandBranchCompany;
import com.gongsibao.ma.base.ISellingDemandBranchCompanyService;

@Service
public class SellingDemandBranchCompanyService extends PersistableService<SellingDemandBranchCompany> implements ISellingDemandBranchCompanyService {

    public SellingDemandBranchCompanyService(){
        super();
        this.type=SellingDemandBranchCompany.class;
    }
}