package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.IBaseServiceProviderConfigerService;
import com.gongsibao.entity.crm.BaseServiceProviderConfiger;

@Service
public class BaseServiceProviderConfigerService extends PersistableService<BaseServiceProviderConfiger> implements IBaseServiceProviderConfigerService{
	public BaseServiceProviderConfigerService() {
		super();
		this.type = BaseServiceProviderConfiger.class;
	}
}
