package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NDepRefund;
import com.gongsibao.trade.base.INDepRefundService;

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
