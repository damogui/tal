package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.IBaseServiceProviderService;
import com.gongsibao.entity.crm.base.ServiceProvider;

@Service
public class BaseServiceProviderService extends PersistableService<ServiceProvider> implements IBaseServiceProviderService{
	public BaseServiceProviderService() {
		super();
		this.type = ServiceProvider.class;
	}
}
