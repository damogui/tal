package com.gongsibao.crm.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.DetailPart;

import com.gongsibao.crm.base.INCustomerTaskFoolowService;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;

public class NCustomerFollowPart extends DetailPart{
	INCustomerTaskFoolowService taskFoolowService = ServiceFactory.create(INCustomerTaskFoolowService.class);
	
	public NCustomerTaskFoolow save(NCustomerTaskFoolow entity) {
		taskFoolowService.save(entity);
		return entity;
	}
}
