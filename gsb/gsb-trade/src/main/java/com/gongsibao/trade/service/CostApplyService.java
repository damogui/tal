package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.CostApply;
import com.gongsibao.trade.base.ICostApplyService;

@Service
public class CostApplyService extends PersistableService<CostApply> implements ICostApplyService {

    public CostApplyService(){
        super();
        this.type=CostApply.class;
    }
}