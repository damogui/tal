package com.gongsibao.supplier.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.supplier.SalesmanServiceScope;
import com.gongsibao.supplier.base.ISalesmanServiceScopeService;

@Service
public class SalesmanServiceScopeService extends PersistableService<SalesmanServiceScope> implements ISalesmanServiceScopeService {

	public SalesmanServiceScopeService() {
		super();
		this.type = SalesmanServiceScope.class;
	}
}