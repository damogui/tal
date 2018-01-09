package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.IBaseServiceProviderService;
import com.gongsibao.entity.crm.BaseServiceProvider;

@Service
public class BaseServiceProviderService extends PersistableService<BaseServiceProvider> implements IBaseServiceProviderService{
	public BaseServiceProviderService() {
		super();
		this.type = BaseServiceProvider.class;
	}
}
