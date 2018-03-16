package com.gongsibao.trade.base;

import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dto.DepPayMapDTO;
import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;
import org.netsharp.entity.IPersistable;

import java.util.List;
import java.util.Map;

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
    int saveNDepReceivableBySoder(DepPayMapDTO entity);
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
