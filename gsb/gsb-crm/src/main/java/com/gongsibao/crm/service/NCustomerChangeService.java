package com.gongsibao.crm.service;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerChangeService;
import com.gongsibao.entity.crm.NCustomerChange;

@Service
public class NCustomerChangeService extends SupplierPersistableService<NCustomerChange> implements INCustomerChangeService {

	public NCustomerChangeService() {
		super();
		this.type = NCustomerChange.class;
	}
}