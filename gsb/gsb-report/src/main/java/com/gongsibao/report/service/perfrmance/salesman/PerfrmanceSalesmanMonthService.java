package com.gongsibao.report.service.perfrmance.salesman;

import java.util.Date;

import com.gongsibao.report.service.perfrmance.AbstractPerfrmanceService;

public class PerfrmanceSalesmanMonthService extends AbstractPerfrmanceService{

	@Override
	public void before() {
		
		this.setNextService(new PerfrmanceSalesmanSeasonService());
	}
	
	@Override
	public void doExecute(Integer principalId,Date date) {

		//执行单人季统计
		this.nextService.execute(principalId, date);
	}

}
