package com.gongsibao.supplier.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.supplier.SupplierServiceScope;
import com.gongsibao.supplier.base.ISupplierServiceScopeService;

@Service
public class SupplierServiceScopeService extends PersistableService<SupplierServiceScope> implements ISupplierServiceScopeService {

	public SupplierServiceScopeService() {
		super();
		this.type = SupplierServiceScope.class;
	}
}