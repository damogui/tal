package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.CompanyValidity;
import com.gongsibao.yj.base.ICompanyValidityService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class CompanyValidityService extends PersistableService<CompanyValidity> implements ICompanyValidityService {

    public CompanyValidityService(){
        super();
        this.type=CompanyValidity.class;
    }
}