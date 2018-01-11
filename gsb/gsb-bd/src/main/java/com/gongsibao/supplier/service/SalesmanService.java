package com.gongsibao.supplier.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.supplier.base.ISalesmanService;

@Service
public class SalesmanService extends PersistableService<Salesman> implements ISalesmanService {

	public SalesmanService() {
		super();
		this.type = Salesman.class;
	}
}