package com.gongsibao.crm.service;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerTaskInspectionService;
import com.gongsibao.entity.crm.NCustomerTaskInspection;

public class NCustomerTaskInspectionService extends SupplierPersistableService<NCustomerTaskInspection> implements
INCustomerTaskInspectionService {

	public NCustomerTaskInspectionService() {
		super();
		this.type = NCustomerTaskInspection.class;
	}
}
