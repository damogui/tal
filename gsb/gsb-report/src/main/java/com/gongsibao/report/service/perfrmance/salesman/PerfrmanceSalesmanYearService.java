package com.gongsibao.report.service.perfrmance.salesman;

import java.util.Date;

import com.gongsibao.report.service.perfrmance.AbstractPerfrmanceService;
import com.gongsibao.report.service.perfrmance.department.PerfrmanceDepartmentDayService;

public class PerfrmanceSalesmanYearService extends AbstractPerfrmanceService {

	@Override
	public void before() {

		this.setNextService(new PerfrmanceDepartmentDayService());
	}

	@Override
	public void doExecute(Integer principalId, Date date) {

		// 执行单人年统计
		this.nextService.execute(principalId, date);
	}

}
