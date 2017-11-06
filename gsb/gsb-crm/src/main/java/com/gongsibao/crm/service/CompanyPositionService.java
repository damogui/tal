package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.ICompanyPositionService;
import com.gongsibao.entity.crm.CompanyPosition;

@Service
public class CompanyPositionService extends PersistableService<CompanyPosition> implements ICompanyPositionService {

    public CompanyPositionService(){
        super();
        this.type=CompanyPosition.class;
    }
}