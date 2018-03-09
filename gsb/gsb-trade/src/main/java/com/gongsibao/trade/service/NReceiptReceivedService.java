package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NReceiptReceived;
import com.gongsibao.trade.base.INReceiptReceivedService;

/**
 * Created by win on 2018/2/27.
 */
@Service
public class NReceiptReceivedService extends PersistableService<NReceiptReceived> implements INReceiptReceivedService {

    public NReceiptReceivedService(){
        super();
        this.type=NReceiptReceived.class;
    }
}
