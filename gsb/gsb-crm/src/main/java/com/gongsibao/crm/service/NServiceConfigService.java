package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.INServiceConfigService;
import com.gongsibao.entity.crm.NServiceConfig;

@Service
public class NServiceConfigService extends PersistableService<NServiceConfig> implements INServiceConfigService {

	public NServiceConfigService() {
		super();
		this.type = NServiceConfig.class;
	}
}