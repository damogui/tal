package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NDepCost;
import com.gongsibao.trade.base.INDepCostService;

/**
 * Created by win on 2018/2/27.
 */
@Service
public class NDepCostService extends PersistableService<NDepCost> implements INDepCostService {

    public NDepCostService(){
        super();
        this.type=NDepCost.class;
    }
}
