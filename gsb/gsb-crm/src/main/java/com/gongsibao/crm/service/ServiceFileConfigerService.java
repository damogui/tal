package com.gongsibao.crm.service;

import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.IServiceFileConfigerService;
import com.gongsibao.entity.crm.ServiceFileConfiger;

public class ServiceFileConfigerService extends PersistableService<ServiceFileConfiger> implements IServiceFileConfigerService{
	public ServiceFileConfigerService() {
		super();
		this.type = ServiceFileConfiger.class;
	}
}
