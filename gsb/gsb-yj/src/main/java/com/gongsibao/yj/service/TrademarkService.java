package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.Trademark;
import com.gongsibao.yj.base.ITrademarkService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class TrademarkService extends PersistableService<Trademark> implements ITrademarkService {

    public TrademarkService(){
        super();
        this.type=Trademark.class;
    }
}