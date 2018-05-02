package com.gongsibao.rest.base.order;

import com.gongsibao.entity.Result;
import com.gongsibao.entity.trade.SoOrder;
import com.netsharp.rest.dto.coupon.CouponUseDTO;
import com.netsharp.rest.dto.order.OrderAddDTO;
import com.netsharp.rest.common.result.Pager;
import com.netsharp.rest.dto.order.OrderDTO;
import com.netsharp.rest.dto.order.OrderMessageDTO;
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
    /**
     * 分页查询我的订单
     *
     * @param accountId   账号ID
     * @param status      要查询的订单状态 (0:全部,1:未付款,2:进行中,3:已完成)
     * @param currentPage 当前页
     * @param pageSize    每页显示条数
     * @return
     */
    Pager<OrderDTO> pageMyOrder(Integer accountId,Integer status, int currentPage, int pageSize);

    /* *
     * @Description: 推送消息处，查看订单详情
     * @param
     * @return
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/21
     */
    OrderMessageDTO getOrderMessage(Integer orderProdId);

    /**
     * 取消订单
     *
     * @param accountId         账号ID
     * @param orderId           订单ID
     */
    @Transaction
    void updateToCancel(Integer accountId, Integer orderId);
}
