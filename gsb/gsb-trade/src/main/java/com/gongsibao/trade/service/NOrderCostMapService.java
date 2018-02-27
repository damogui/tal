package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.NOrderCostMap;
import com.gongsibao.entity.trade.OrderCps;
import com.gongsibao.trade.base.INOrderCostMapService;
import com.gongsibao.trade.base.IOrderCpsService;
import org.netsharp.service.PersistableService;

/**
 * Created by win on 2018/2/27.
 */
public class NOrderCostMapService extends PersistableService<NOrderCostMap> implements INOrderCostMapService {

    public NOrderCostMapService(){
        super();
        this.type=NOrderCostMap.class;
    }
}
