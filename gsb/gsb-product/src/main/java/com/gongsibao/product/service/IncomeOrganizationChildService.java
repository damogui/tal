package com.gongsibao.product.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.IncomeOrganizationChild;
import com.gongsibao.product.base.IIncomeOrganizationChildService;

@Service
public class IncomeOrganizationChildService extends PersistableService<IncomeOrganizationChild> implements IIncomeOrganizationChildService {

    public IncomeOrganizationChildService(){
        super();
        this.type=IncomeOrganizationChild.class;
    }
}