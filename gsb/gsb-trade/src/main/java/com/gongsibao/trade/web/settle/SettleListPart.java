package com.gongsibao.trade.web.settle;

import com.gongsibao.trade.base.settle.ISettleService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

public class SettleListPart extends ListPart {

    ISettleService settleService = ServiceFactory.create(ISettleService.class);



}
