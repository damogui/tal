package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NPrice;
import com.gongsibao.trade.base.INPriceService;

/**
 * Created by win on 2018/2/27.
 */
@Service
public class NPriceService extends PersistableService<NPrice> implements INPriceService {

    public NPriceService(){
        super();
        this.type=NPrice.class;
    }
}
