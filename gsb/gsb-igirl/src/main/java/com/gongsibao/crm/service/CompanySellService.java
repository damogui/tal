package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.ICompanySellService;
import com.gongsibao.entity.crm.CompanySell;

@Service
public class CompanySellService extends PersistableService<CompanySell> implements ICompanySellService {

    public CompanySellService(){
        super();
        this.type=CompanySell.class;
    }
}