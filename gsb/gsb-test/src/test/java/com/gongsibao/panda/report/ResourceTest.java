package com.gongsibao.panda.report;

import org.junit.Before;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.report.PerformanceStatistics;
import com.gongsibao.report.base.IPerformanceStatisticsService;

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
		node1 = this.createResourceNodeCategory("业绩统计", prefix + "_Perfrmance", node.getId());
		{
			this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "部门_日统计", node1.getCode() + "_Department_Day", IPerformanceStatisticsService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "部门_周统计", node1.getCode() + "_Department_Week", IPerformanceStatisticsService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "部门_月统计", node1.getCode() + "_Department_Month", IPerformanceStatisticsService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "部门_季统计", node1.getCode() + "_Department_Season", IPerformanceStatisticsService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "部门_年统计", node1.getCode() + "_Department_Year", IPerformanceStatisticsService.class.getName(), node1.getId());
			
			
			this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "业务员_日统计", node1.getCode() + "_Salesman_Day", IPerformanceStatisticsService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "业务员_周统计", node1.getCode() + "_Salesman_Week", IPerformanceStatisticsService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "业务员_月统计", node1.getCode() + "_Salesman_Month", IPerformanceStatisticsService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "业务员_季统计", node1.getCode() + "_Salesman_Season", IPerformanceStatisticsService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PerformanceStatistics.class.getName(), "业务员_年统计", node1.getCode() + "_Salesman_Year", IPerformanceStatisticsService.class.getName(), node1.getId());
			
		}

	}
}
