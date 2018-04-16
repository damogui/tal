package com.gongsibao.trade.web.interactive;

import com.gongsibao.entity.trade.OrderProdTrace;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.trade.base.IOrderProdTraceService;
import com.gongsibao.utils.NumberUtils;
import org.apache.commons.lang.StringUtils;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.panda.commerce.AdvancedListPart;

import java.util.List;

public class MyInChargeListPart extends AdvancedListPart {
    IOrderProdTraceService orderProdTraceService = ServiceFactory.create(IOrderProdTraceService.class);
    //添加跟进
    public void addFollowUp(Integer orderProdId, String followContent) {
        orderProdTraceService.addFollowUp(orderProdId, followContent);
    }

}
