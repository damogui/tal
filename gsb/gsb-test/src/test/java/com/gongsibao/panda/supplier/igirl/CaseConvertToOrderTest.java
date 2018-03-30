package com.gongsibao.panda.supplier.igirl;

import com.gongsibao.entity.igirl.res.ConvertToOrderResult;
import com.gongsibao.igirl.tm.base.ITradeMarkCaseService;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;

public class CaseConvertToOrderTest {
    ITradeMarkCaseService tradeMarkCaseService = ServiceFactory.create(ITradeMarkCaseService.class);
    @Test
    public void testConvertToOrder() {
        ConvertToOrderResult result = tradeMarkCaseService.convertToOrder("7");
        System.out.println(result);
    }
}
