package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.CompanyPlayerEmployeMap;
import com.gongsibao.yj.base.ICompanyPlayerEmployeMapService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class CompanyPlayerEmployeMapService extends PersistableService<CompanyPlayerEmployeMap> implements ICompanyPlayerEmployeMapService {

    public CompanyPlayerEmployeMapService(){
        super();
        this.type=CompanyPlayerEmployeMap.class;
    }
}