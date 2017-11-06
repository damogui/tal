package com.gongsibao.cms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.IRecommendCategoryService;
import com.gongsibao.entity.cms.RecommendCategory;

@Service
public class RecommendCategoryService extends PersistableService<RecommendCategory> implements IRecommendCategoryService {

    public RecommendCategoryService(){
        super();
        this.type=RecommendCategory.class;
    }
}