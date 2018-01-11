package com.gongsibao.supplier.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.supplier.SupplierFunctionModule;
import com.gongsibao.supplier.base.ISupplierFunctionModuleService;

@Service
public class SupplierFunctionModuleService extends PersistableService<SupplierFunctionModule> implements ISupplierFunctionModuleService {

	public SupplierFunctionModuleService() {
		super();
		this.type = SupplierFunctionModule.class;
	}
}