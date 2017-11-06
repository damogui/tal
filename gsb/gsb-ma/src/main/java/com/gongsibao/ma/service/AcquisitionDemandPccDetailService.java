package com.gongsibao.ma.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.ma.AcquisitionDemandPccDetail;
import com.gongsibao.ma.base.IAcquisitionDemandPccDetailService;

@Service
public class AcquisitionDemandPccDetailService extends PersistableService<AcquisitionDemandPccDetail> implements IAcquisitionDemandPccDetailService {

	public AcquisitionDemandPccDetailService() {
		
		super();
		this.type = AcquisitionDemandPccDetail.class;
		// TODO Auto-generated constructor stub
	}

}
