package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.NProductType;
import com.gongsibao.entity.trade.OrderCps;
import com.gongsibao.trade.base.INProductTypeService;
import com.gongsibao.trade.base.IOrderCpsService;
import org.netsharp.service.PersistableService;

/**
 * Created by win on 2018/2/27.
 */
public class NProductTypeService extends PersistableService<NProductType> implements INProductTypeService {

    public NProductTypeService(){
        super();
        this.type=NProductType.class;
    }
}
