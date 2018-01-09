package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.ICompanyPlayerPositionMapService;
import com.gongsibao.entity.crm.CompanyPlayerPositionMap;

@Service
public class CompanyPlayerPositionMapService extends PersistableService<CompanyPlayerPositionMap> implements ICompanyPlayerPositionMapService {

    public CompanyPlayerPositionMapService(){
        super();
        this.type=CompanyPlayerPositionMap.class;
    }
}