package com.gongsibao.rest.demo;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class SsalesOrderService extends PersistableService<SalesOrder> implements ISalesOrderService {
	
	public SsalesOrderService() {
		super();
		
		this.type = SalesOrder.class;
	}

}
