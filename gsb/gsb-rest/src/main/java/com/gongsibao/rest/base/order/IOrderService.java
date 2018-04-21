package com.gongsibao.rest.base.order;

import com.gongsibao.entity.Result;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.rest.dto.coupon.CouponUseDTO;
import com.gongsibao.rest.dto.order.OrderAddDTO;
import com.gongsibao.trade.web.dto.OrderPayDTO;
import org.netsharp.core.annotations.Transaction;

/**
 * ClassName: IOrderService
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: TODO
 * @date
 */
public interface IOrderService {

    SoOrder getById(Integer orderId);

    @Transaction
    Result<SoOrder> saveOrder(OrderAddDTO orderAddDTO);

    Result<CouponUseDTO> findOrderCoupon(OrderAddDTO orderAddDTO);

    Integer countByAccountId(Integer accountId, boolean isPaid);

    void updateOnlinePay(OrderPayDTO orderPayDTO);
}
