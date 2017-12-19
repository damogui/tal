package com.gongsibao.panda;

import java.util.List;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import com.gongsibao.entity.report.PerformanceStatistics;
import com.gongsibao.franchisee.job.FranchiseeReportJob;
import com.gongsibao.report.base.IPerformanceStatisticsService;
import com.gongsibao.uc.base.IOrganizationService;

public class MyTest {
	@Test
	public void run() {
		FranchiseeReportJob myJob =new FranchiseeReportJob();
		myJob.execute(null);
		System.out.println("ok......");
		
		/*IPerformanceStatisticsService service =ServiceFactory.create(IPerformanceStatisticsService.class);
		Oql oql = new Oql();{
			
			oql.setType(PerformanceStatistics.class);
			oql.setSelects("PerformanceStatistics.*,department.shortName");
		}
		
		List<PerformanceStatistics> list = service.queryList(oql);*/
		
	}
}
