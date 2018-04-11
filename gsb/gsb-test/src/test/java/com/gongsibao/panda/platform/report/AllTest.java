package com.gongsibao.panda.platform.report;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.platform.report.workspace.customer.ComprehenStatisticalWorkspaceTest;
import com.gongsibao.panda.platform.report.workspace.customer.DayReportWorkspaceTest;
import com.gongsibao.panda.platform.report.workspace.customer.DistrictReportWorkspaceTest;
import com.gongsibao.panda.platform.report.workspace.customer.FollowStatisticalWorkspaceTest;
import com.gongsibao.panda.platform.report.workspace.customer.FunnelStatisticalWorkspaceTest;
import com.gongsibao.panda.platform.report.workspace.customer.MonthReportWorkspaceTest;
import com.gongsibao.panda.platform.report.workspace.customer.ProductReportWorkspaceTest;
import com.gongsibao.panda.platform.report.workspace.customer.SourceReportWorkspaceTest;
import com.gongsibao.panda.platform.report.workspace.customer.StatisticalCustomerServiceWorkspaceTest;
import com.gongsibao.panda.platform.report.workspace.customer.StatusReportWorkspaceTest;
import com.gongsibao.panda.platform.report.workspace.customer.WeekReportWorkspaceTest;
import com.gongsibao.panda.platform.report.workspace.customer.YearReportWorkspaceTest;

@RunWith(Suite.class)
@SuiteClasses({

	ResourceTest.class, 

	//销售业绩：部门
	com.gongsibao.panda.platform.report.workspace.perfrmance.department.DayWorkspaceTest.class,
	com.gongsibao.panda.platform.report.workspace.perfrmance.department.MonthWorkspaceTest.class,
	com.gongsibao.panda.platform.report.workspace.perfrmance.department.SeasonWorkspaceTest.class,
	com.gongsibao.panda.platform.report.workspace.perfrmance.department.WeekWorkspaceTest.class,
	com.gongsibao.panda.platform.report.workspace.perfrmance.department.YearWorkspaceTest.class,
	
	//销售业绩：业务员
	 com.gongsibao.panda.platform.report.workspace.perfrmance.salesman.DayWorkspaceTest.class,
	 com.gongsibao.panda.platform.report.workspace.perfrmance.salesman.MonthWorkspaceTest.class,
	 com.gongsibao.panda.platform.report.workspace.perfrmance.salesman.SeasonWorkspaceTest.class,
	 com.gongsibao.panda.platform.report.workspace.perfrmance.salesman.WeekWorkspaceTest.class,
	 com.gongsibao.panda.platform.report.workspace.perfrmance.salesman.YearWorkspaceTest.class,
	
//	 //产品按部门统计
//	 com.gongsibao.panda.report.workspace.product.department.DayWorkspaceTest.class,
//	 com.gongsibao.panda.report.workspace.product.department.MonthWorkspaceTest.class,
//	 com.gongsibao.panda.report.workspace.product.department.SeasonWorkspaceTest.class,
//	 com.gongsibao.panda.report.workspace.product.department.WeekWorkspaceTest.class,
//	 com.gongsibao.panda.report.workspace.product.department.YearWorkspaceTest.class,
//	 
//	 //产品按地区统计
//	 com.gongsibao.panda.report.workspace.product.district.DayWorkspaceTest.class,
//	 com.gongsibao.panda.report.workspace.product.district.MonthWorkspaceTest.class,
//	 com.gongsibao.panda.report.workspace.product.district.SeasonWorkspaceTest.class,
//	 com.gongsibao.panda.report.workspace.product.district.WeekWorkspaceTest.class,
//	 com.gongsibao.panda.report.workspace.product.district.YearWorkspaceTest.class,

	 
	 DayReportWorkspaceTest.class,
	 MonthReportWorkspaceTest.class,
	 WeekReportWorkspaceTest.class,
	 YearReportWorkspaceTest.class,
	 
	 DistrictReportWorkspaceTest.class,
	 ProductReportWorkspaceTest.class,
	 SourceReportWorkspaceTest.class,
	 StatusReportWorkspaceTest.class,
	 
     /*ComprehenStatisticalWorkspaceTest.class,
     FollowStatisticalWorkspaceTest.class,
     FunnelStatisticalWorkspaceTest.class,
     StatisticalCustomerServiceWorkspaceTest.class,*/
     
	 NavigationTest.class
	
})

public class AllTest {

}
