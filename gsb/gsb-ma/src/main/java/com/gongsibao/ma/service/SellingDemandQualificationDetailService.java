package com.gongsibao.ma.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.ma.DemandQualificationDetail;
import com.gongsibao.ma.base.ISellingDemandQualificationDetailService;

@Service
public class SellingDemandQualificationDetailService extends PersistableService<DemandQualificationDetail> implements ISellingDemandQualificationDetailService{

    public SellingDemandQualificationDetailService(){
        super();
        this.type=DemandQualificationDetail.class;
    }
}