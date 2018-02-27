package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.NMarketingCost;
import com.gongsibao.entity.trade.OrderCps;
import com.gongsibao.trade.base.INMarketingCostService;
import com.gongsibao.trade.base.IOrderCpsService;
import org.netsharp.service.PersistableService;

/**
 * Created by win on 2018/2/27.
 */
public class NMarketingCostService extends PersistableService<NMarketingCost> implements INMarketingCostService {

    public NMarketingCostService(){
        super();
        this.type=NMarketingCost.class;
    }
}
