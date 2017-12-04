package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.PatentAgency;
import com.gongsibao.yj.base.IPatentAgencyService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class PatentAgencyService extends PersistableService<PatentAgency> implements IPatentAgencyService {

    public PatentAgencyService(){
        super();
        this.type=PatentAgency.class;
    }
}