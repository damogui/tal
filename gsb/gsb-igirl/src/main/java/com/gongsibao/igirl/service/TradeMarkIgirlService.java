package com.gongsibao.igirl.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.igirl.base.ITradeMarkIgirlService;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
@Service
public class TradeMarkIgirlService extends GsbPersistableService<TradeMark> implements ITradeMarkIgirlService {
    ITradeMarkIgirlService tradeMarkIgirlService = ServiceFactory.create(ITradeMarkIgirlService.class);
}
