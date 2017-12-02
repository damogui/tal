package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.CompanyAvgScore;
import com.gongsibao.yj.base.ICompanyAvgScoreService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class CompanyAvgScoreService extends PersistableService<CompanyAvgScore> implements ICompanyAvgScoreService {

    public CompanyAvgScoreService(){
        super();
        this.type=CompanyAvgScore.class;
    }
}