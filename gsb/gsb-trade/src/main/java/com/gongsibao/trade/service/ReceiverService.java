package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.Receiver;
import com.gongsibao.trade.base.IReceiverService;

@Service
public class ReceiverService extends PersistableService<Receiver> implements IReceiverService {

    public ReceiverService(){
        super();
        this.type=Receiver.class;
    }
}