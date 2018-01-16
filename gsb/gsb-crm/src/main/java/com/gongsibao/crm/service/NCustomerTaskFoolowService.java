package com.gongsibao.crm.service;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerTaskFoolowService;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;

@Service
public class NCustomerTaskFoolowService extends SupplierPersistableService<NCustomerTaskFoolow> implements INCustomerTaskFoolowService {

	public NCustomerTaskFoolowService() {
		super();
		this.type = NCustomerTaskFoolow.class;
	}
}