package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.trade.base.INDepReceivableService;

/**
 * Created by win on 2018/2/27.
 */
@Service
public class NDepReceivableService extends PersistableService<NDepReceivable> implements INDepReceivableService {

    public NDepReceivableService(){
        super();
        this.type=NDepReceivable.class;
    }
}
