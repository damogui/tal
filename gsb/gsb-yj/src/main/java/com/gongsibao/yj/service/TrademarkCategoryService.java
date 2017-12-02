package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.TrademarkCategory;
import com.gongsibao.yj.base.ITrademarkCategoryService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class TrademarkCategoryService extends PersistableService<TrademarkCategory> implements ITrademarkCategoryService {

    public TrademarkCategoryService(){
        super();
        this.type=TrademarkCategory.class;
    }
}