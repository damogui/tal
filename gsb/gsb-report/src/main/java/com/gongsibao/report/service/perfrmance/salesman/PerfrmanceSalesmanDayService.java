package com.gongsibao.report.service.perfrmance.salesman;

import java.util.Date;

import com.gongsibao.report.service.perfrmance.AbstractPerfrmanceService;
import com.gongsibao.report.service.perfrmance.department.PerfrmanceDepartmentWeekService;

public class PerfrmanceSalesmanDayService extends AbstractPerfrmanceService{

	@Override
	public void doExecute(Integer principalId,Date date) {

	}

	@Override
	public void before() {
		
		this.setNextService(new PerfrmanceDepartmentWeekService());
	}
}
