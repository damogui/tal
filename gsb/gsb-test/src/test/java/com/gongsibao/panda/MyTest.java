package com.gongsibao.panda;

import java.util.Date;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.util.DateManage;

import com.gongsibao.franchisee.base.IFranchiseeReportService;

public class MyTest {
	@Test
	public void run() {
		// FranchiseeReportJob myJob =new FranchiseeReportJob();
		// myJob.execute(null);
		// System.out.println("ok......");

		/*
		 * IPerformanceStatisticsService service
		 * =ServiceFactory.create(IPerformanceStatisticsService.class); Oql oql
		 * = new Oql();{
		 * 
		 * oql.setType(PerformanceStatistics.class);
		 * oql.setSelects("PerformanceStatistics.*,department.shortName"); }
		 * 
		 * List<PerformanceStatistics> list = service.queryList(oql);
		 */

		IFranchiseeReportService frService = ServiceFactory.create(IFranchiseeReportService.class);

		for (int i = 10; i <= 10; i++) {

			Date date = DateManage.parse("2017-12-" + i);
			frService.generate(date);
		}
	}
}
