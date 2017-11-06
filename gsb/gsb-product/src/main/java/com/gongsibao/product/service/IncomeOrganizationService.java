package com.gongsibao.product.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.IncomeOrganization;
import com.gongsibao.product.base.IIncomeOrganizationService;

@Service
public class IncomeOrganizationService extends PersistableService<IncomeOrganization> implements IIncomeOrganizationService {

    public IncomeOrganizationService(){
        super();
        this.type=IncomeOrganization.class;
    }
}