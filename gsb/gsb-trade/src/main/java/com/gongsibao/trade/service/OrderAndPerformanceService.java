package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.INOrderAndPerformanceService;
import com.gongsibao.trade.base.IOrderService;
import org.netsharp.service.PersistableService;

/**
 * 订单业绩和回款业绩
 */
public class OrderAndPerformanceService extends PersistableService<SoOrder> implements INOrderAndPerformanceService {

    public OrderAndPerformanceService() {
        super ();
        this.type = SoOrder.class;
    }

    @Override
    public Boolean applyCarryover(NOrderCarryover orderCarryover) {
        return null;
    }
}
