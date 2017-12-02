package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.TrademarkImg;
import com.gongsibao.yj.base.ITrademarkImgService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class TrademarkImgService extends PersistableService<TrademarkImg> implements ITrademarkImgService {

    public TrademarkImgService(){
        super();
        this.type=TrademarkImg.class;
    }
}