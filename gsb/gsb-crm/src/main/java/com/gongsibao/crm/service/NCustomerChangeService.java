package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.INCustomerChangeService;
import com.gongsibao.entity.crm.NCustomerChange;

@Service
public class NCustomerChangeService extends PersistableService<NCustomerChange> implements INCustomerChangeService {

	public NCustomerChangeService() {
		super();
		this.type = NCustomerChange.class;
	}
}