package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.entity.crm.NCustomer;

@Service
public class NCustomerService extends PersistableService<NCustomer> implements INCustomerService {

	public NCustomerService() {
		super();
		this.type = NCustomer.class;
	}
}