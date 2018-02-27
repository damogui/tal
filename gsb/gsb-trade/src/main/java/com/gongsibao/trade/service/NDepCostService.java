package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.NDepCost;
import com.gongsibao.entity.trade.OrderCps;
import com.gongsibao.trade.base.INDepCostService;
import com.gongsibao.trade.base.IOrderCpsService;
import org.netsharp.service.PersistableService;

/**
 * Created by win on 2018/2/27.
 */
public class NDepCostService extends PersistableService<NDepCost> implements INDepCostService {

    public NDepCostService(){
        super();
        this.type=NDepCost.class;
    }
}
