package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.CompanyOpexception;
import com.gongsibao.yj.base.ICompanyOpexceptionService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class CompanyOpexceptionService extends PersistableService<CompanyOpexception> implements ICompanyOpexceptionService {

    public CompanyOpexceptionService(){
        super();
        this.type=CompanyOpexception.class;
    }
}