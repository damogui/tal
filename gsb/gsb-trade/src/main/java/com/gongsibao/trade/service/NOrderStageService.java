package com.gongsibao.trade.service;

import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NOrderStage;
import com.gongsibao.trade.base.INOrderStageService;

/**
 * Created by win on 2018/2/27.
 */
public class NOrderStageService extends PersistableService<NOrderStage> implements INOrderStageService {

    public NOrderStageService(){
        super();
        this.type=NOrderStage.class;
    }
}
