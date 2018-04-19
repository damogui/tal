package com.gongsibao.rest.service.order;

import com.gongsibao.entity.Result;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.rest.dto.order.OrderAddDTO;
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

}
