package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;
import com.gongsibao.crm.base.IServiceFileService;
import com.gongsibao.entity.crm.ServiceFile;

@Service
public class ServiceFileService extends PersistableService<ServiceFile> implements IServiceFileService{
	public ServiceFileService() {
		super();
		this.type = ServiceFile.class;
	}
}
