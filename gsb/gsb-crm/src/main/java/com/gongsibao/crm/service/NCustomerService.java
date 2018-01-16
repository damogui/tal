package com.gongsibao.crm.service;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.entity.crm.NCustomer;

@Service
public class NCustomerService extends SupplierPersistableService<NCustomer> implements INCustomerService {

	public NCustomerService() {
		super();
		this.type = NCustomer.class;
	}
}