package com.gongsibao.crm.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.FormNavigation;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.crm.base.ICustomerService;
import com.gongsibao.entity.crm.Customer;

public class CustomerFormPart extends FormPart{

	ICustomerService customerService = ServiceFactory.create(ICustomerService.class);
	public Customer validationContactWay(Integer id,String contactWay,String type){
	
		return customerService.validationContactWay(id,contactWay, type);
	}
	
	public Customer save(Customer entity) {

		entity = customerService.save(entity);
		entity = customerService.byId(entity.getId());
		return entity;
	}

	public FormNavigation byId(Object id) {

		FormNavigation navigation = this.createFormNavigation(id);
		IPersistable obj = customerService.byId(id);
		if (obj == null) {
			navigation.Entity = this.newInstance(null);
		} else {
			navigation.Entity = obj;
		}

		return navigation;
	}
	
	public FormNavigation bySwtCustomerId(String swtCustomerId) {

		FormNavigation navigation = this.createFormNavigation(id);
		IPersistable obj = customerService.bySwtCustomerId(swtCustomerId);
		if (obj == null) {
			
			navigation.Entity = this.newInstance(null);
		} else {
			navigation.Entity = obj;
		}

		return navigation;
	}
	
	public FormNavigation byContactWay(String contactWay,String type) {

		//type:mobile,telephone,qq,weixin
		FormNavigation navigation = this.createFormNavigation(id);
		IPersistable obj = customerService.byContactWay(contactWay,type);
		navigation.Entity = obj;

		return navigation;
	}
	
	public FormNavigation bindSwtCustomerId(String swtCustomerId,int customerId){
		
		FormNavigation navigation = this.createFormNavigation(id);
		IPersistable obj = customerService.bindSwtCustomerId(swtCustomerId,customerId);
		navigation.Entity = obj;

		return navigation;
	}
}
