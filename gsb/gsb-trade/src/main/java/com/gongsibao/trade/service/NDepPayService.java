package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.entity.trade.OrderCps;
import com.gongsibao.trade.base.INDepPayService;
import com.gongsibao.trade.base.IOrderCpsService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

/**
 * Created by win on 2018/2/27.
 */
@Service
public class NDepPayService extends PersistableService<NDepPay> implements INDepPayService {

    public NDepPayService(){
        super();
        this.type=NDepPay.class;
    }
}
