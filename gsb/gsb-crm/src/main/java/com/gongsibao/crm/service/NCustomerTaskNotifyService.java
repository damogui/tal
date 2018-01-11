package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.entity.crm.NCustomerTaskNotify;

@Service
public class NCustomerTaskNotifyService extends PersistableService<NCustomerTaskNotify> implements INCustomerTaskNotifyService {

	public NCustomerTaskNotifyService() {
		super();
		this.type = NCustomerTaskNotify.class;
	}
}