package com.gongsibao.bd.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.bd.base.IPreferentialDataMapService;
import com.gongsibao.entity.bd.PreferentialDataMap;

@Service
public class PreferentialDataMapService extends PersistableService<PreferentialDataMap> implements IPreferentialDataMapService {

    public PreferentialDataMapService(){
        super();
        this.type=PreferentialDataMap.class;
    }
}