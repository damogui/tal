package com.gongsibao.bd.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.bd.base.ISyncService;
import com.gongsibao.entity.bd.Sync;

@Service
public class SyncService extends PersistableService<Sync> implements ISyncService {

    public SyncService(){
        super();
        this.type=Sync.class;
    }
}