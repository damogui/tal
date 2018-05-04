package com.gongsibao.igirl.tm.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.tm.TradeMarkCase;
import com.gongsibao.igirl.tm.base.ITradeMarkCaseIgirlService;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
@Service
public class TradeMarkCaseIgirlService extends GsbPersistableService<TradeMarkCase> implements ITradeMarkCaseIgirlService {
    ITradeMarkCaseIgirlService tradeMarkCaseIgirlService = ServiceFactory.create(ITradeMarkCaseIgirlService.class);
}
