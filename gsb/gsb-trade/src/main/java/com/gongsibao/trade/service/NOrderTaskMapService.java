package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.NOrderTaskMap;
import com.gongsibao.entity.trade.OrderCps;
import com.gongsibao.trade.base.INOrderTaskMapService;
import com.gongsibao.trade.base.IOrderCpsService;
import org.netsharp.service.PersistableService;

/**
 * Created by win on 2018/2/27.
 */
public class NOrderTaskMapService extends PersistableService<NOrderTaskMap> implements INOrderTaskMapService {

    public NOrderTaskMapService(){
        super();
        this.type=NOrderTaskMap.class;
    }
}
