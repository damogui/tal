package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NOrderCostMap;
import com.gongsibao.trade.base.INOrderCostMapService;

/**
 * Created by win on 2018/2/27.
 */
@Service
public class NOrderCostMapService extends PersistableService<NOrderCostMap> implements INOrderCostMapService {

    public NOrderCostMapService(){
        super();
        this.type=NOrderCostMap.class;
    }
}
