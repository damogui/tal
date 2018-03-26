package com.gongsibao.igirl.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.igirl.base.ITradeMarkCaseIgirlService;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
@Service
public class TradeMarkCaseIgirlService extends GsbPersistableService<TradeMarkCase> implements ITradeMarkCaseIgirlService {
    ITradeMarkCaseIgirlService tradeMarkCaseIgirlService = ServiceFactory.create(ITradeMarkCaseIgirlService.class);
}
