package com.gongsibao.trade.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;
import org.netsharp.entity.IPersistable;

import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.SoOrder;

/**
 * 订单业绩和回款业绩
 */
public interface INOrderAndPerformanceService extends IPersistableService<SoOrder> {


    /**
     * @Title: applyCarryover
     * @Description: TODO(创建回款业绩)
     * @param: @return
     * @return: Boolean
     * @throws
     */
    @Transaction
    int saveNDepReceivableBySoder(Pay entity);
    /**
     * @Title: applyCarryover
     * @Description: TODO(创建订单业绩)
     * @param: @return
     * @return: Boolean
     * @throws
     */
    @Transaction
    IPersistable saveOrderPerformance(SoOrder entity);
}
