package com.gongsibao.panda.supplier.igirl.service;

import com.gongsibao.entity.Result;
import com.gongsibao.entity.igirl.res.ConvertToOrderResult;
import com.gongsibao.entity.igirl.settle.Settle;
import com.gongsibao.entity.trade.dic.SettleStatus;
import com.gongsibao.igirl.settle.base.IOrderProdCaseService;
import com.gongsibao.igirl.settle.base.ISettleService;
import com.gongsibao.igirl.tm.base.ITradeMarkCaseService;
import com.gongsibao.trade.base.IOrderProdService;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;

import java.util.Arrays;

/**
 * service单元测试
 */
public class SettleServiceTest {
    ITradeMarkCaseService tradeMarkCaseService = ServiceFactory.create(ITradeMarkCaseService.class);
    IOrderProdService orderProdService = ServiceFactory.create(IOrderProdService.class);
    IOrderProdCaseService orderProdCaseService = ServiceFactory.create(IOrderProdCaseService.class);
    ISettleService settleService = ServiceFactory.create(ISettleService.class);
    @Test
    public void testConvertToOrder() {
//        ConvertToOrderResult result = tradeMarkCaseService.convertToOrder("9");
//        ConvertToOrderResult result = tradeMarkCaseService.convertToOrder("3", "100264343");
//        System.out.println(result);

//        boolean b = orderProdService.updateSettleStatus(Arrays.asList(1), SettleStatus.NO_SETTLEMENT);
        Result<Settle> settleResult = settleService.saveSettle(Arrays.asList(1, 2, 3));
        System.out.println(settleResult);
    }
}
