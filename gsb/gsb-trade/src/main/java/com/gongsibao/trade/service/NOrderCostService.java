package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NOrderCost;
import com.gongsibao.trade.base.INOrderCostService;

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
