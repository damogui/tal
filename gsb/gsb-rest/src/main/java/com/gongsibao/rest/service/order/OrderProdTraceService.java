package com.gongsibao.rest.service.order;

import com.gongsibao.rest.base.order.IOrderProdTraceService;
import com.gongsibao.rest.web.dto.order.OrderProdTraceDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 订单项跟进记录
 * @date 2018/4/21 12:01
 */
@Service
public class OrderProdTraceService implements IOrderProdTraceService {


    @Override
    public List<OrderProdTraceDTO> queryTraceByCondition(Integer orderProdId,List<Integer> typeIds) {
        return null;
    }
}
