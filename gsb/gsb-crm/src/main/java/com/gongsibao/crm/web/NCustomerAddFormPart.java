package com.gongsibao.crm.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.entity.crm.NCustomer;

public class NCustomerAddFormPart extends FormPart{

	INCustomerService customerService = ServiceFactory.create(INCustomerService.class);
	public NCustomer validationContactWay(Integer id,String contactWay,String type){
	
		return customerService.validationContactWay(id,contactWay, type);
	}
}
