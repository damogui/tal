package com.gongsibao.supplier.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.supplier.SupplierServiceProduct;
import com.gongsibao.supplier.base.ISupplierServiceProductService;

@Service
public class SupplierServiceProductService extends PersistableService<SupplierServiceProduct> implements ISupplierServiceProductService {

	public SupplierServiceProductService() {
		super();
		this.type = SupplierServiceProduct.class;
	}
}