package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.NCostReceiptMap;
import com.gongsibao.entity.trade.OrderCps;
import com.gongsibao.trade.base.INCostReceiptMapService;
import com.gongsibao.trade.base.IOrderCpsService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

/**
 * Created by win on 2018/2/27.
 */
@Service
public class NCostReceiptMapService extends PersistableService<NCostReceiptMap> implements INCostReceiptMapService {

    public NCostReceiptMapService(){
        super();
        this.type=NCostReceiptMap.class;
    }
}
