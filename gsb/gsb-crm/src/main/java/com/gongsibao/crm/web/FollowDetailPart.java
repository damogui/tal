package com.gongsibao.crm.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.DetailPart;

import com.gongsibao.crm.base.ICustomerFollowService;
import com.gongsibao.entity.crm.CustomerFollow;

public class FollowDetailPart extends DetailPart{

	ICustomerFollowService service = ServiceFactory.create(ICustomerFollowService.class);
	public CustomerFollow save(CustomerFollow entity){
		
		entity = service.save(entity);
		return entity;
	}
}
