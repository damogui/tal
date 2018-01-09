package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.INCustomerProductService;
import com.gongsibao.entity.crm.NCustomerProduct;

@Service
public class NCustomerProductService extends PersistableService<NCustomerProduct> implements INCustomerProductService {

	public NCustomerProductService() {
		super();
		this.type = NCustomerProduct.class;
	}
}