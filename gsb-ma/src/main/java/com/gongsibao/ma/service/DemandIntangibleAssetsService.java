package com.gongsibao.ma.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.ma.DemandIntangibleAssets;
import com.gongsibao.ma.base.IDemandIntangibleAssetsService;

@Service
public class DemandIntangibleAssetsService extends PersistableService<DemandIntangibleAssets> implements IDemandIntangibleAssetsService {

    public DemandIntangibleAssetsService(){
        super();
        this.type=DemandIntangibleAssets.class;
    }
}