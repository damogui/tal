package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.Refund;
import com.gongsibao.trade.base.IRefundService;

@Service
public class RefundService extends PersistableService<Refund> implements IRefundService {

    public RefundService(){
        super();
        this.type=Refund.class;
    }
}