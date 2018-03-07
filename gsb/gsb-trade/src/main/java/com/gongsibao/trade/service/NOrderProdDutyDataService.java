package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.NOrderProdDutyData;
import com.gongsibao.entity.trade.OrderCps;
import com.gongsibao.trade.base.INOrderProdDutyDataService;
import com.gongsibao.trade.base.IOrderCpsService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

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
