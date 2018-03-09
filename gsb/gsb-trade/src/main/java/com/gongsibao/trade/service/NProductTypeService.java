package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NProductType;
import com.gongsibao.trade.base.INProductTypeService;

/**
 * Created by win on 2018/2/27.
 */
@Service
public class NProductTypeService extends PersistableService<NProductType> implements INProductTypeService {

    public NProductTypeService(){
        super();
        this.type=NProductType.class;
    }
}
