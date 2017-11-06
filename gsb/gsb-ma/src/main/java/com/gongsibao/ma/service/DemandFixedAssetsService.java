package com.gongsibao.ma.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.ma.DemandFixedAssets;
import com.gongsibao.ma.base.IDemandFixedAssetsService;


@Service
public class DemandFixedAssetsService extends PersistableService<DemandFixedAssets> implements IDemandFixedAssetsService {

    public DemandFixedAssetsService(){
        super();
        this.type=DemandFixedAssets.class;
    }
}