package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.ICompanyBuyService;
import com.gongsibao.entity.crm.CompanyBuy;

@Service
public class CompanyBuyService extends PersistableService<CompanyBuy> implements ICompanyBuyService {

    public CompanyBuyService(){
        super();
        this.type=CompanyBuy.class;
    }
}