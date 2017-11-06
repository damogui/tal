package com.gongsibao.cms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.ISpecialActiveService;
import com.gongsibao.entity.cms.SpecialActive;

@Service
public class SpecialActiveService extends PersistableService<SpecialActive> implements ISpecialActiveService {

    public SpecialActiveService(){
        super();
        this.type=SpecialActive.class;
    }
}