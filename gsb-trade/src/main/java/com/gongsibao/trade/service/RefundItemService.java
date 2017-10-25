package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.RefundItem;
import com.gongsibao.trade.base.IRefundItemService;

@Service
public class RefundItemService extends PersistableService<RefundItem> implements IRefundItemService {

    public RefundItemService(){
        super();
        this.type=RefundItem.class;
    }
}