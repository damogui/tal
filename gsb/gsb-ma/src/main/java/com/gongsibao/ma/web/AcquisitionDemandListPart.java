package com.gongsibao.ma.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.entity.ma.dic.DemandSoldOutState;
import com.gongsibao.ma.base.IAcquisitionDemandService;

public class AcquisitionDemandListPart extends ListPart{

	
	public boolean changeSoldOutState(int id,DemandSoldOutState soldOutState){
		
		IAcquisitionDemandService service = ServiceFactory.create(IAcquisitionDemandService.class);
		return service.changeSoldOutState(id,soldOutState);
	}
}
