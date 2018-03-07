package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.NOrderCost;
import com.gongsibao.entity.trade.OrderCps;
import com.gongsibao.trade.base.INOrderCostService;
import com.gongsibao.trade.base.IOrderCpsService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

/**
 * Created by win on 2018/2/27.
 */
@Service
public class NOrderCostService extends PersistableService<NOrderCost> implements INOrderCostService {

    public NOrderCostService(){
        super();
        this.type=NOrderCost.class;
    }
}
