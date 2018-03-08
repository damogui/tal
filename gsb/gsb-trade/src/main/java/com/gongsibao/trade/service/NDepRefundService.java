package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.NDepRefund;
import com.gongsibao.entity.trade.OrderCps;
import com.gongsibao.trade.base.INDepRefundService;
import com.gongsibao.trade.base.IOrderCpsService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

/**
 * Created by win on 2018/2/27.
 */
@Service
public class NDepRefundService extends PersistableService<NDepRefund> implements INDepRefundService {

    public NDepRefundService(){
        super();
        this.type=NDepRefund.class;
    }
}
