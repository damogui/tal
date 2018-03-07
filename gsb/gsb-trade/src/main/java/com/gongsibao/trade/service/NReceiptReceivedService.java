package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.NReceiptReceived;
import com.gongsibao.entity.trade.OrderCps;
import com.gongsibao.trade.base.INReceiptReceivedService;
import com.gongsibao.trade.base.IOrderCpsService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

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
