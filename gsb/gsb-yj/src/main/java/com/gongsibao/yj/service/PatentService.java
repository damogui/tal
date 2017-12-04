package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.Patent;
import com.gongsibao.yj.base.IPatentService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class PatentService extends PersistableService<Patent> implements IPatentService {

    public PatentService(){
        super();
        this.type=Patent.class;
    }
}