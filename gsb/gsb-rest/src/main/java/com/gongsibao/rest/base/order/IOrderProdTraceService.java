package com.gongsibao.rest.base.order;

import com.netsharp.rest.dto.order.OrderProdTraceDTO;

import java.util.List;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 订单项跟进记录
 * @date 2018/4/21 11:59
 */
public interface IOrderProdTraceService {

    /**
     * 查询进度跟进条件
     *
     * @param orderProdId 订单产品ID
     * @param typeIds 类型ID集合
     * @return
     */
    List<OrderProdTraceDTO> queryTraceByCondition(Integer orderProdId,List<Integer> typeIds);
}
