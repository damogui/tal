package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.TrademarkRecord;
import com.gongsibao.yj.base.ITrademarkRecordService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class TrademarkRecordService extends PersistableService<TrademarkRecord> implements ITrademarkRecordService {

    public TrademarkRecordService(){
        super();
        this.type=TrademarkRecord.class;
    }
}