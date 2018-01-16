package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.INCustomerTaskQualityService;
import com.gongsibao.entity.crm.NCustomerTaskQuality;

@Service
public class NCustomerTaskQualityService extends PersistableService<NCustomerTaskQuality> implements INCustomerTaskQualityService {

	public NCustomerTaskQualityService() {
		super();
		this.type = NCustomerTaskQuality.class;
	}
}