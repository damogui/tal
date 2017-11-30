package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.Suppler;
import com.gongsibao.uc.base.ISupplerService;

@Service
public class SupplerService extends PersistableService<Suppler> implements ISupplerService {

    public SupplerService(){
        super();
        this.type=Suppler.class;
    }
}