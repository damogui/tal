package com.gongsibao.crm.service;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerProductService;
import com.gongsibao.entity.crm.NCustomerProduct;

@Service
public class NCustomerProductService extends SupplierPersistableService<NCustomerProduct> implements INCustomerProductService {

	public NCustomerProductService() {
		super();
		this.type = NCustomerProduct.class;
	}
}