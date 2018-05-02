package com.gongsibao.rest.service.order;

import com.gongsibao.entity.trade.OrderProdTrace;
import com.gongsibao.rest.base.order.IOrderProdTraceService;
import com.netsharp.rest.dto.order.OrderProdTraceDTO;
import org.apache.commons.lang3.BooleanUtils;
import org.netsharp.communication.ServiceFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 订单项跟进记录
 * @date 2018/4/21 12:01
 */
@Service
public class OrderProdTraceService implements IOrderProdTraceService {

    private com.gongsibao.trade.base.IOrderProdTraceService orderProdTraceService = ServiceFactory.create(com.gongsibao.trade.base.IOrderProdTraceService.class);

    @Override
    public List<OrderProdTraceDTO> queryTraceByCondition(Integer orderProdId,List<Integer> typeIds) {
        List<OrderProdTraceDTO> list = new ArrayList<>();
        List<OrderProdTrace> orderProdTraces = orderProdTraceService.byOrderProdIdTypeIds(orderProdId, typeIds);
        if(orderProdTraces!=null){
            return orderProdTraces.stream().map(orderProdTrace -> {
                OrderProdTraceDTO orderProdTraceDTO = new OrderProdTraceDTO();
                BeanUtils.copyProperties(orderProdTrace,orderProdTraceDTO);
                orderProdTraceDTO.setPkid(orderProdTrace.getId());
                orderProdTraceDTO.setOrderProdId(orderProdTrace.getOrderProdId());
                orderProdTraceDTO.setOrderProdStatusId(orderProdTrace.getOrderProdStatusId());
                orderProdTraceDTO.setTypeId(orderProdTrace.getTypeId().getValue());
                orderProdTraceDTO.setOperatorType(orderProdTrace.getOperatorType().getValue());
                orderProdTraceDTO.setOperatorId(orderProdTrace.getOperatorType().getValue());
                orderProdTraceDTO.setInfo(orderProdTrace.getInfo());
                orderProdTraceDTO.setOperatorId(orderProdTrace.getOperatorId());
                orderProdTraceDTO.setAddTime(orderProdTrace.getCreateTime());
                orderProdTraceDTO.setRemark(orderProdTrace.getRemark());
                orderProdTraceDTO.setIsSendSms(BooleanUtils.toInteger(orderProdTrace.getIsSendSms(),1,0));
                orderProdTraceDTO.setProcessdDays(orderProdTrace.getProcessdDays());
                orderProdTraceDTO.setTimeoutDays(orderProdTrace.getTimeoutDays());
                orderProdTraceDTO.setTipColor(orderProdTrace.getTipColor());
                if(orderProdTrace.getOrderProdStatus()!=null){
                    orderProdTraceDTO.setOrderProdStatusName(orderProdTrace.getOrderProdStatus().getName());
                }
                if(orderProdTrace.getOperator()!=null){
                    orderProdTraceDTO.setOperatorName(orderProdTrace.getOperator().getName());
                }
                return orderProdTraceDTO;
            }).collect(Collectors.toList());
        }
        return list;
    }
}
