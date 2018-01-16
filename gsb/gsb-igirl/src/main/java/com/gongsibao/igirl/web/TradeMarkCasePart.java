package com.gongsibao.igirl.web;
import com.gongsibao.igirl.base.ITradeMarkCaseService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;


public class TradeMarkCasePart extends FormPart {
    ITradeMarkCaseService service = ServiceFactory.create(ITradeMarkCaseService.class);



}
