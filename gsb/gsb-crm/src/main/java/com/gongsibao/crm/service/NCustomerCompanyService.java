package com.gongsibao.crm.service;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerCompanyService;
import com.gongsibao.entity.crm.NCustomerCompany;

@Service
public class NCustomerCompanyService extends SupplierPersistableService<NCustomerCompany> implements INCustomerCompanyService {

	public NCustomerCompanyService() {
		super();
		this.type = NCustomerCompany.class;
	}
}