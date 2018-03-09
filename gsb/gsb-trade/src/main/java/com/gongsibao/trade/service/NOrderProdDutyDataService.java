package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NOrderProdDutyData;
import com.gongsibao.trade.base.INOrderProdDutyDataService;

/**
 * Created by win on 2018/2/27.
 */
@Service
public class NOrderProdDutyDataService extends PersistableService<NOrderProdDutyData> implements INOrderProdDutyDataService {

    public NOrderProdDutyDataService(){
        super();
        this.type=NOrderProdDutyData.class;
    }
}
