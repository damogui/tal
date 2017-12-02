package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.TrademarkFlow;
import com.gongsibao.yj.base.ITrademarkFlowService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class TrademarkFlowService extends PersistableService<TrademarkFlow> implements ITrademarkFlowService {

    public TrademarkFlowService(){
        super();
        this.type=TrademarkFlow.class;
    }
}