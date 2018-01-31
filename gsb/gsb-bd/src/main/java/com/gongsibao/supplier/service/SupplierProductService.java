package com.gongsibao.supplier.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.supplier.SupplierProduct;
import com.gongsibao.supplier.base.ISupplierProductService;

@Service
public class SupplierProductService extends PersistableService<SupplierProduct> implements ISupplierProductService {

	public SupplierProductService() {
		super();
		this.type = SupplierProduct.class;
	}
}