package com.gongsibao.igirl.settle.web;

import com.gongsibao.igirl.settle.base.ISettleService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

public class SettleListPart extends ListPart {

    ISettleService settleService = ServiceFactory.create(ISettleService.class);



}
