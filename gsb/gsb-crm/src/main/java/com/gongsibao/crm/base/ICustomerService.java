package com.gongsibao.crm.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.crm.Customer;

public interface ICustomerService extends IPersistableService<Customer> {
	
	Customer validationContactWay(Integer id,String contactWay,String type);
	
	Customer bySwtCustomerId(String swtCustomerId);
}