package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.CostApplyMap;
import com.gongsibao.trade.base.ICostApplyMapService;

@Service
public class CostApplyMapService extends PersistableService<CostApplyMap> implements ICostApplyMapService {

    public CostApplyMapService(){
        super();
        this.type=CostApplyMap.class;
    }
}