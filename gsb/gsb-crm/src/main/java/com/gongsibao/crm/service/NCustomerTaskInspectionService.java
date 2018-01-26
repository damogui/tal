package com.gongsibao.crm.service;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerTaskInspectionService;
import com.gongsibao.entity.crm.NCustomerTaskInspection;

@Service
public class NCustomerTaskInspectionService extends SupplierPersistableService<NCustomerTaskInspection> implements
INCustomerTaskInspectionService {

	public NCustomerTaskInspectionService() {
		super();
		this.type = NCustomerTaskInspection.class;
	}
}
