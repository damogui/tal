package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.INCustomerTaskFoolowService;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;

@Service
public class NCustomerTaskFoolowService extends PersistableService<NCustomerTaskFoolow> implements INCustomerTaskFoolowService {

	public NCustomerTaskFoolowService() {
		super();
		this.type = NCustomerTaskFoolow.class;
	}
}