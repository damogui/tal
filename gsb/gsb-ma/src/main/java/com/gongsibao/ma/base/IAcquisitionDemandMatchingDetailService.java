package com.gongsibao.ma.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.ma.AcquisitionDemandMatchingDetail;

public interface IAcquisitionDemandMatchingDetailService  extends IPersistableService<AcquisitionDemandMatchingDetail>{

	List<AcquisitionDemandMatchingDetail> getByAcquisitionDemandId(Integer acquisitionDemandId);
	
	boolean refresh(Integer acquisitionDemandId);
}
