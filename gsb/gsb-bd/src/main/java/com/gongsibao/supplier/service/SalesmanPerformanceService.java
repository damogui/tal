package com.gongsibao.supplier.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.supplier.SalesmanPerformance;
import com.gongsibao.supplier.base.ISalesmanPerformanceServcie;

@Service
public class SalesmanPerformanceService extends PersistableService<SalesmanPerformance> implements ISalesmanPerformanceServcie {

	public SalesmanPerformanceService() {
		super();
		this.type = SalesmanPerformance.class;
	}
}