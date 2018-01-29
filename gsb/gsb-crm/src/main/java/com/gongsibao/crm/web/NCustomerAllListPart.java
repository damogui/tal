package com.gongsibao.crm.web;

import org.netsharp.communication.ServiceFactory;

import com.gongsibao.crm.base.INCustomerService;

public class NCustomerAllListPart extends NCustomerBaseListPart{

	/**
	 * 开通会员功能
	 * @param customerId
	 * @return
	 */
	public boolean openMember(Integer customerId){
		
		INCustomerService customerService = ServiceFactory.create(INCustomerService.class);
		return customerService.openMember(customerId);
	}
}
