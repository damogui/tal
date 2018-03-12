package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.trade.base.INDepPayService;

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
