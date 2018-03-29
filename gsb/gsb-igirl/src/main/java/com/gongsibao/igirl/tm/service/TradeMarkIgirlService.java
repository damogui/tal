package com.gongsibao.igirl.tm.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.tm.TradeMark;
import com.gongsibao.igirl.tm.base.ITradeMarkIgirlService;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
@Service
public class TradeMarkIgirlService extends GsbPersistableService<TradeMark> implements ITradeMarkIgirlService {
    ITradeMarkIgirlService tradeMarkIgirlService = ServiceFactory.create(ITradeMarkIgirlService.class);
}
