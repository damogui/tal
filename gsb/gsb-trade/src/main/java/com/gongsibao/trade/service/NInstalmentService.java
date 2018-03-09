package com.gongsibao.trade.service;

import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NInstalment;
import com.gongsibao.trade.base.INInstalmentService;

/**
 * Created by win on 2018/2/27.
 */
public class NInstalmentService extends PersistableService<NInstalment> implements INInstalmentService {

    public NInstalmentService(){
        super();
        this.type=NInstalment.class;
    }
}
