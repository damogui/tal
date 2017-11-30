package com.gongsibao.ma.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.ma.SellingDemandTurnoverDetail;
import com.gongsibao.ma.base.ISellingDemandTurnoverDetailService;

@Service
public class SellingDemandTurnoverDetailService extends PersistableService<SellingDemandTurnoverDetail> implements ISellingDemandTurnoverDetailService {

    public SellingDemandTurnoverDetailService(){
        super();
        this.type=SellingDemandTurnoverDetail.class;
    }
}