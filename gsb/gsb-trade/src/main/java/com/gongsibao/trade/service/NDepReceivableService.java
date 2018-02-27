package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.entity.trade.OrderCps;
import com.gongsibao.trade.base.INDepReceivableService;
import com.gongsibao.trade.base.IOrderCpsService;
import org.netsharp.service.PersistableService;

/**
 * Created by win on 2018/2/27.
 */
public class NDepReceivableService extends PersistableService<NDepReceivable> implements INDepReceivableService {

    public NDepReceivableService(){
        super();
        this.type=NDepReceivable.class;
    }
}
