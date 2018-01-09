package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.ICustomerOrderService;
import com.gongsibao.entity.crm.CustomerOrder;

@Service
public class CustomerOrderService extends PersistableService<CustomerOrder> implements ICustomerOrderService {

	public CustomerOrderService() {
		super();
		this.type = CustomerOrder.class;
	}

}
