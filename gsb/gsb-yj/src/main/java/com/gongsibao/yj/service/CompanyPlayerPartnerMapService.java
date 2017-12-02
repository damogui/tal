package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.CompanyPlayerPartnerMap;
import com.gongsibao.yj.base.ICompanyPlayerPartnerMapService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class CompanyPlayerPartnerMapService extends PersistableService<CompanyPlayerPartnerMap> implements ICompanyPlayerPartnerMapService {

    public CompanyPlayerPartnerMapService(){
        super();
        this.type=CompanyPlayerPartnerMap.class;
    }
}