package com.gongsibao.bd.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.bd.base.IDivinationService;
import com.gongsibao.entity.bd.Divination;

@Service
public class DivinationService extends PersistableService<Divination> implements IDivinationService {

    public DivinationService(){
        super();
        this.type=Divination.class;
    }
}