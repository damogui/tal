package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.RefundItemPrice;
import com.gongsibao.trade.base.IRefundItemPriceService;

@Service
public class RefundItemPriceService extends PersistableService<RefundItemPrice> implements IRefundItemPriceService {

    public RefundItemPriceService(){
        super();
        this.type=RefundItemPrice.class;
    }
}