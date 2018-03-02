package com.gongsibao.trade.web;

import com.gongsibao.entity.trade.OrderProdTrace;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IOrderProdTraceService;
import com.gongsibao.u8.base.ISoOrderService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import java.util.List;

/**
 * Created by win on 2018/3/2.
 */
public class SalesmanAllOrderListPart extends ListPart {
    ISoOrderService prodTraceService = ServiceFactory.create(ISoOrderService.class);

//    public List<SoOrder> querySoOrderTraceList(Integer soOrderId) {
//        List<SoOrder> orderTraceList = prodTraceService.queryList (soOrderId);
//        return orderTraceList;
//    }
}
