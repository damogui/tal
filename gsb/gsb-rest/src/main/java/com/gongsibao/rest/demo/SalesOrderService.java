package com.gongsibao.rest.demo;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class SalesOrderService extends PersistableService<SalesOrder> implements ISalesOrderService {
	
	public SalesOrderService() {
		super();
		
		this.type = SalesOrder.class;
	}

}
