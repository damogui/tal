package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NOrderTaskMap;
import com.gongsibao.trade.base.INOrderTaskMapService;

/**
 * Created by win on 2018/2/27.
 */
@Service
public class NOrderTaskMapService extends PersistableService<NOrderTaskMap> implements INOrderTaskMapService {

    public NOrderTaskMapService(){
        super();
        this.type=NOrderTaskMap.class;
    }
}
