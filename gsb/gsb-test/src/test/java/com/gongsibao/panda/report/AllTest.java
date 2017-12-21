package com.gongsibao.panda.report;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({

	ResourceTest.class, 

	//销售业绩：部门
	com.gongsibao.panda.report.workspace.perfrmance.department.DayWorkspaceTest.class,
	com.gongsibao.panda.report.workspace.perfrmance.department.MonthWorkspaceTest.class,
	com.gongsibao.panda.report.workspace.perfrmance.department.SeasonWorkspaceTest.class,
	com.gongsibao.panda.report.workspace.perfrmance.department.WeekWorkspaceTest.class,
	com.gongsibao.panda.report.workspace.perfrmance.department.YearWorkspaceTest.class,
	
	//销售业绩：业务员
	 com.gongsibao.panda.report.workspace.perfrmance.salesman.DayWorkspaceTest.class,
	 com.gongsibao.panda.report.workspace.perfrmance.salesman.MonthWorkspaceTest.class,
	 com.gongsibao.panda.report.workspace.perfrmance.salesman.SeasonWorkspaceTest.class,
	 com.gongsibao.panda.report.workspace.perfrmance.salesman.WeekWorkspaceTest.class,
	 com.gongsibao.panda.report.workspace.perfrmance.salesman.YearWorkspaceTest.class,
	
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
//	 
//	 
//	 //客户按部门统计
	 com.gongsibao.panda.report.workspace.customer.department.DayWorkspaceTest.class,
	 com.gongsibao.panda.report.workspace.customer.department.MonthWorkspaceTest.class,
//	 com.gongsibao.panda.report.workspace.customer.department.SeasonWorkspaceTest.class,
	 com.gongsibao.panda.report.workspace.customer.department.WeekWorkspaceTest.class,
	 com.gongsibao.panda.report.workspace.customer.department.YearWorkspaceTest.class,
//	 
//	 //客户按地区统计
//	 com.gongsibao.panda.report.workspace.customer.district.DayWorkspaceTest.class,
//	 com.gongsibao.panda.report.workspace.customer.district.MonthWorkspaceTest.class,
//	 com.gongsibao.panda.report.workspace.customer.district.SeasonWorkspaceTest.class,
//	 com.gongsibao.panda.report.workspace.customer.district.WeekWorkspaceTest.class,
//	 com.gongsibao.panda.report.workspace.customer.district.YearWorkspaceTest.class,
//	 
//	 //客户按业务员统计
//	 com.gongsibao.panda.report.workspace.customer.salesman.DayWorkspaceTest.class,
//	 com.gongsibao.panda.report.workspace.customer.salesman.MonthWorkspaceTest.class,
//	 com.gongsibao.panda.report.workspace.customer.salesman.SeasonWorkspaceTest.class,
//	 com.gongsibao.panda.report.workspace.customer.salesman.WeekWorkspaceTest.class,
//	 com.gongsibao.panda.report.workspace.customer.salesman.YearWorkspaceTest.class,
	 
	NavigationTest.class
	
})

public class AllTest {

}
