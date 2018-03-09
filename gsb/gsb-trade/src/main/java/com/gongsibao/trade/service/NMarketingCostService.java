package com.gongsibao.trade.service;

import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NMarketingCost;
import com.gongsibao.trade.base.INMarketingCostService;

/**
 * Created by win on 2018/2/27.
 */
public class NMarketingCostService extends PersistableService<NMarketingCost> implements INMarketingCostService {

    public NMarketingCostService(){
        super();
        this.type=NMarketingCost.class;
    }
}
