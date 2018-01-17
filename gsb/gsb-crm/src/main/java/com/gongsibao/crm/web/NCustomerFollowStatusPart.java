package com.gongsibao.crm.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.crm.base.INCustomerTaskFoolowService;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;

public class NCustomerFollowStatusPart extends FormPart{
	INCustomerTaskFoolowService taskFoolowService = ServiceFactory.create(INCustomerTaskFoolowService.class);
	
	public NCustomerTaskFoolow save(NCustomerTaskFoolow entity) {
		entity =  taskFoolowService.save(entity);
		return entity;
	}
}
