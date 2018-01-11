package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerTask;

@Service
public class NCustomerTaskService extends PersistableService<NCustomerTask> implements INCustomerTaskService {

	public NCustomerTaskService() {
		super();
		this.type = NCustomerTask.class;
	}
}