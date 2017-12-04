package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.TrademarkCategoryIndustryMap;
import com.gongsibao.yj.base.ITrademarkCategoryIndustryMapService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class TrademarkCategoryIndustryMapService extends PersistableService<TrademarkCategoryIndustryMap> implements ITrademarkCategoryIndustryMapService {

    public TrademarkCategoryIndustryMapService(){
        super();
        this.type=TrademarkCategoryIndustryMap.class;
    }
}