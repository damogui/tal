package com.gongsibao.ma.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.entity.ma.dic.DemandSoldOutState;
import com.gongsibao.ma.base.ISellingDemandService;

public class SellingDemandListPart extends ListPart{

	public boolean changeSoldOutState(int id,DemandSoldOutState soldOutState){
		
		ISellingDemandService service = ServiceFactory.create(ISellingDemandService.class);
		return service.changeSoldOutState(id,soldOutState);
	}
}
