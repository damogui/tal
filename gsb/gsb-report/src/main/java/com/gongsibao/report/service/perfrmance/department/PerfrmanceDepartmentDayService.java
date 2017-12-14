package com.gongsibao.report.service.perfrmance.department;

import java.util.Date;

import com.gongsibao.report.service.perfrmance.AbstractPerfrmanceService;
import com.gongsibao.report.service.perfrmance.salesman.PerfrmanceSalesmanMonthService;

public class PerfrmanceDepartmentDayService extends AbstractPerfrmanceService{
 
	public void doExecute(Integer principalId,Date date){
		
		
	}

	@Override
	public void before() {

		this.setNextService(new PerfrmanceSalesmanMonthService());
	}

}
