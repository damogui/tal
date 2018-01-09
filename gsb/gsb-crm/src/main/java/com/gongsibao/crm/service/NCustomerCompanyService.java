package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.INCustomerCompanyService;
import com.gongsibao.entity.crm.NCustomerCompany;

@Service
public class NCustomerCompanyService extends PersistableService<NCustomerCompany> implements INCustomerCompanyService {

	public NCustomerCompanyService() {
		super();
		this.type = NCustomerCompany.class;
	}
}