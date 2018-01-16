package com.gongsibao.crm.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.crm.base.INCustomerTaskService;

public class NCustomerFormPart extends FormPart{
	INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);

	
	public boolean updateNcustomerTask(Integer taskId) {
		System.out.println(taskId);
		return true;
	}
}
