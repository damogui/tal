package com.gongsibao.cms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.IRecommendServiceService;
import com.gongsibao.entity.cms.RecommendService;

@Service
public class RecommendServiceService extends PersistableService<RecommendService> implements IRecommendServiceService {

    public RecommendServiceService(){
        super();
        this.type=RecommendService.class;
    }
}