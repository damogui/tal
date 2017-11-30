package com.gongsibao.cms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.IRecommendPackageService;
import com.gongsibao.entity.cms.RecommendPackage;

@Service
public class RecommendPackageService extends PersistableService<RecommendPackage> implements IRecommendPackageService {

    public RecommendPackageService(){
        super();
        this.type=RecommendPackage.class;
    }
}