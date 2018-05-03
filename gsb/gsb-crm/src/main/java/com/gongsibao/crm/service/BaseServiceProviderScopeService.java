package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.IBaseServiceProviderScopeService;
import com.gongsibao.entity.crm.base.ServiceProviderScope;

@Service
public class BaseServiceProviderScopeService extends PersistableService<ServiceProviderScope> implements IBaseServiceProviderScopeService{
	public BaseServiceProviderScopeService() {
		super();
		this.type = ServiceProviderScope.class;
	}
}
