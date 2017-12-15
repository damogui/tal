package com.gongsibao.report.service.perfrmance.department;

import com.gongsibao.report.service.perfrmance.AbstractPerfrmanceService;

public class PerfrmanceDepartmentDayService extends AbstractPerfrmanceService{
 
	public void doExecute(){
		
		
	}

	@Override
	public void before() {
		AbstractPerfrmanceService netService = new PerfrmanceDepartmentWeekService();
		netService.setContext(context);
		this.setNextService(netService);
	}

	@Override
	public Boolean delete() {
		// TODO Auto-generated method stub
		return true;
	}

}
