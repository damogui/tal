package com.gongsibao.crm.service;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerTaskQualityService;
import com.gongsibao.entity.crm.NCustomerTaskQuality;

@Service
public class NCustomerTaskQualityService extends SupplierPersistableService<NCustomerTaskQuality> implements INCustomerTaskQualityService {

	public NCustomerTaskQualityService() {
		super();
		this.type = NCustomerTaskQuality.class;
	}
}