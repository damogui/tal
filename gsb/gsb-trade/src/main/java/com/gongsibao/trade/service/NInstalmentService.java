package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.NInstalment;
import com.gongsibao.entity.trade.OrderCps;
import com.gongsibao.trade.base.INInstalmentService;
import com.gongsibao.trade.base.IOrderCpsService;
import org.netsharp.service.PersistableService;

/**
 * Created by win on 2018/2/27.
 */
public class NInstalmentService extends PersistableService<NInstalment> implements INInstalmentService {

    public NInstalmentService(){
        super();
        this.type=NInstalment.class;
    }
}
