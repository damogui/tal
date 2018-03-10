package com.gongsibao.panda.platform.report;

import org.junit.Before;
import org.netsharp.base.IPersistableService;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.report.PerformanceStatistics;
import com.gongsibao.entity.report.customer.BaseCustomerReportEntity;
import com.gongsibao.entity.report.customer.CustomerDistrictReport;
import com.gongsibao.entity.report.customer.CustomerProductReport;
import com.gongsibao.entity.report.customer.CustomerSourceReport;
import com.gongsibao.entity.report.customer.CustomerStatusReport;
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
		
		node1 = this.createResourceNodeCategory("客户统计", prefix + "_Customer", node.getId());
		{
			this.createResourceNodeVoucher(BaseCustomerReportEntity.class.getName(), "年统计", node1.getCode() + "_Year", IPersistableService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(BaseCustomerReportEntity.class.getName(), "月统计", node1.getCode() + "_Moth", IPersistableService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(BaseCustomerReportEntity.class.getName(), "周统计", node1.getCode() + "_Week", IPersistableService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(BaseCustomerReportEntity.class.getName(), "日统计", node1.getCode() + "_Day", IPersistableService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(CustomerProductReport.class.getName(), "意向产品统计", node1.getCode() + "_Product", IPersistableService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(CustomerDistrictReport.class.getName(), "意向地区统计", node1.getCode() + "_District", IPersistableService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(CustomerStatusReport.class.getName(), "客户状态统计", node1.getCode() + "_Status", IPersistableService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(CustomerSourceReport.class.getName(), "客户来源统计", node1.getCode() + "_Source", IPersistableService.class.getName(), node1.getId());
			
		}
		
	}
}
