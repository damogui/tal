package com.gongsibao.crm.service;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerTask;

@Service
public class NCustomerTaskService extends SupplierPersistableService<NCustomerTask> implements INCustomerTaskService {

	public NCustomerTaskService() {
		super();
		this.type = NCustomerTask.class;
	}
}