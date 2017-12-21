package com.gongsibao.report.service.customer;

import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.report.PerformanceStatistics;
import com.gongsibao.report.base.customer.ICustomerReportService;

@Service
public class CustomerReportService extends PersistableService<PerformanceStatistics> implements ICustomerReportService {

	public CustomerReportService() {
		super();
		this.type = PerformanceStatistics.class;
	}
	
	public List<PerformanceStatistics> queryList(Oql oql){
		
		return null;
	}
}