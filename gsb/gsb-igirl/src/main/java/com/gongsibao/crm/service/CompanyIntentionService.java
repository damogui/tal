package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.ICompanyIntentionService;
import com.gongsibao.entity.crm.CompanyIntention;

@Service
public class CompanyIntentionService extends PersistableService<CompanyIntention> implements ICompanyIntentionService {

    public CompanyIntentionService(){
        super();
        this.type=CompanyIntention.class;
    }
}