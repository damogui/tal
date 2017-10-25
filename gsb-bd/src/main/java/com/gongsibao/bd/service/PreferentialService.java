package com.gongsibao.bd.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.bd.base.IPreferentialService;
import com.gongsibao.entity.bd.Preferential;

@Service
public class PreferentialService extends PersistableService<Preferential> implements IPreferentialService {

    public PreferentialService(){
        super();
        this.type=Preferential.class;
    }
}