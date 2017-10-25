package com.gongsibao.cms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.ICooperationService;
import com.gongsibao.entity.cms.Cooperation;

@Service
public class CooperationService extends PersistableService<Cooperation> implements ICooperationService {

    public CooperationService(){
        super();
        this.type=Cooperation.class;
    }
}