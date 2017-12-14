package com.gongsibao.report.service.perfrmance.department;

import java.util.Date;

import com.gongsibao.report.service.perfrmance.AbstractPerfrmanceService;

public class PerfrmanceDepartmentSeasonService extends AbstractPerfrmanceService{

	@Override
	public void doExecute(Integer principalId,Date date) {
		
		
	}
	
	@Override
	public void before() {
		
		this.setNextService(new PerfrmanceDepartmentYearService());
	}
}
