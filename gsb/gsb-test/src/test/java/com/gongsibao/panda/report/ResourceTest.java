package com.gongsibao.panda.report;

import org.junit.Before;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.report.PerformanceStatistics;
import com.gongsibao.entity.report.customer.CustomerDayGrowView;
import com.gongsibao.entity.report.customer.CustomerMonthGrowView;
import com.gongsibao.entity.report.customer.CustomerWeekGrowView;
import com.gongsibao.entity.report.customer.CustomerYearGrowView;

public class ResourceTest extends ResourceCreationBase{


	public static String resourcePrefix = "GSB_Report";

	@Before
	public void setup() {

		parentNodeName = "报表中心";
		parentNodeCode = ResourceTest.resourcePrefix;
		pluginName = "报表中心";
		seq = 9;
		entityClass = ResourceNode.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		String prefix = ResourceTest.resourcePrefix;
		ResourceNode node1 = null;
		node1 = this.createResourceNodeCategory("部门业绩", prefix + "_Department_Perfrmance", node.getId());
		{
			this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "日统计", node1.getCode() + "_Day", IPerformanceStatisticsService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "周统计", node1.getCode() + "_Week", IPerformanceStatisticsService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "月统计", node1.getCode() + "_Month", IPerformanceStatisticsService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "季统计", node1.getCode() + "_Season", IPerformanceStatisticsService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "年统计", node1.getCode() + "_Year", IPerformanceStatisticsService.class.getName(), node1.getId());

		}

		node1 = this.createResourceNodeCategory("业务员业绩", prefix + "_Salesman_Perfrmance", node.getId());
		{

			this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "日统计", node1.getCode() + "_Day", IPerformanceStatisticsService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "周统计", node1.getCode() + "_Week", IPerformanceStatisticsService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "月统计", node1.getCode() + "_Month", IPerformanceStatisticsService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "季统计", node1.getCode() + "_Season", IPerformanceStatisticsService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "年统计", node1.getCode() + "_Year", IPerformanceStatisticsService.class.getName(), node1.getId());
			
		}
		
		node1 = this.createResourceNodeCategory("客户增长量统计", prefix + "_Customer_Grow", node.getId());
		{

			this.createResourceNodeVoucher(CustomerDayGrowView.class.getName(), "日增长量统计", node1.getCode() + "_Day", ICustomerDayGrowViewService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(CustomerWeekGrowView.class.getName(), "周增长量统计", node1.getCode() + "_Week", ICustomerWeekGrowViewService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(CustomerMonthGrowView.class.getName(), "月增长量统计", node1.getCode() + "_Month", ICustomerMonthGrowViewService.class.getName(), node1.getId());
			/*this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "季增长", node1.getCode() + "_Season", IPerformanceStatisticsService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "半年增长", node1.getCode() + "_HalfYear", IPerformanceStatisticsService.class.getName(), node1.getId());*/			
			this.createResourceNodeVoucher(CustomerYearGrowView.class.getName(), "年增长量统计", node1.getCode() + "_Year", ICustomerYearGrowViewService.class.getName(), node1.getId());
			
		}
		
	}
}
