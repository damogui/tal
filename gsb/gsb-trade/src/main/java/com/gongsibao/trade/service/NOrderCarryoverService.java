package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.trade.base.INOrderCarryoverService;

@Service
public class NOrderCarryoverService extends PersistableService<NOrderCarryover> implements INOrderCarryoverService{

    public NOrderCarryoverService(){
        super();
        this.type=NOrderCarryover.class;
    }
}
