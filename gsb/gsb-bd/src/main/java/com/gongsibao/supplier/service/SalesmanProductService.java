package com.gongsibao.supplier.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.supplier.SalesmanProduct;
import com.gongsibao.supplier.base.ISalesmanProductService;

@Service
public class SalesmanProductService extends PersistableService<SalesmanProduct> implements ISalesmanProductService {

	public SalesmanProductService() {
		super();
		this.type = SalesmanProduct.class;
	}
}