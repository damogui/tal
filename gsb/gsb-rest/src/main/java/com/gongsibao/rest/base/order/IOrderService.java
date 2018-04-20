package com.gongsibao.rest.base.order;

import com.gongsibao.entity.Result;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.rest.web.common.web.Pager;
import com.gongsibao.rest.web.dto.order.OrderAddDTO;
import com.gongsibao.rest.web.dto.order.OrderDTO;
import org.netsharp.core.annotations.Transaction;

/**
 * ClassName: IOrderService
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: TODO
 * @date
 */
public interface IOrderService {

    @Transaction
    Result<SoOrder> saveOrder(OrderAddDTO orderAddDTO);

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

}
