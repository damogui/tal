package com.gongsibao.report.service.perfrmance.department;

import com.gongsibao.report.service.perfrmance.AbstractPerfrmanceService;

public class PerfrmanceDepartmentSeasonService extends AbstractPerfrmanceService{

	@Override
	public void doExecute() {
		
		
	}
	
	@Override
	public void before() {
		AbstractPerfrmanceService netService = new PerfrmanceDepartmentYearService();
		netService.setContext(context);
		this.setNextService(netService);
	}

	@Override
	public Boolean delete() {
		// TODO Auto-generated method stub
		return true;
	}
}
