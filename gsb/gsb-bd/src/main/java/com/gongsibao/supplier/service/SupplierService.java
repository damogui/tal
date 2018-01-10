package com.gongsibao.supplier.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.supplier.base.ISupplierService;

@Service
public class SupplierService extends PersistableService<Supplier> implements ISupplierService {

	public SupplierService() {
		super();
		this.type = Supplier.class;
	}
}