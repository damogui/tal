package com.gongsibao.report.service.perfrmance.salesman;

import java.sql.Types;

import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.MtableManager;
import org.netsharp.core.QueryParameters;
import org.netsharp.util.sqlbuilder.SelectBuilder;

import com.gongsibao.entity.report.PerformanceStatistics;
import com.gongsibao.entity.report.dic.ReportDateType;
import com.gongsibao.entity.report.dic.ReportOrganizationType;
import com.gongsibao.report.service.perfrmance.AbstractPerfrmanceService;

/**
 * @ClassName: PerfrmanceSalesmanWeekService
 * @Description:TODO 业务员周统计，与月无关，单独统计
 * @author: 韩伟
 * @date: 2017年12月14日 下午2:25:36
 * 
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved.
 */
public class PerfrmanceSalesmanWeekService extends AbstractPerfrmanceSalesmanService {

	@Override
	public void before() {

		AbstractPerfrmanceService netService = new PerfrmanceSalesmanMonthService();
		netService.setContext(context);
		this.setNextService(netService);
	}

	@Override
	public void doExecute() {

		// 生成业务员的月报表
		SelectBuilder builder = SelectBuilder.getInstance();
		{
			builder.select("department_id as departmentId", "salesman_id as salesmanId", "year", "week", "SUM(receivable_amount) as receivableAmount", "SUM(paid_amount) as paidAmount",
					"SUM(refund_amount) as refundAmount", "SUM(net_receivables) as netReceivables", "SUM(net_paid_amount) as netPaidAmount", "SUM(product_count) as productCount",
					"SUM(order_count) as orderCount");
			builder.from(MtableManager.getMtable(PerformanceStatistics.class).getTableName());
			builder.where("year=?", "week=?", "date_type=?", "organization_type=?","salesman_id is not null");
			builder.groupBy("salesman_id");
		}

		QueryParameters qps = new QueryParameters();
		qps.add("year", this.context.getYear(), Types.INTEGER);
		qps.add("week", this.context.getWeek(), Types.INTEGER);
		qps.add("dateType", ReportDateType.DAY.getValue(), Types.INTEGER);
		qps.add("organizationType", ReportOrganizationType.SALESMAN.getValue(), Types.INTEGER);
		DataTable dataTable = this.pm.executeTable(builder.toSQL(), qps);
		for (IRow row : dataTable) {
			
			PerformanceStatistics entity =this.create(row);
			this.getStatisticsService().save(entity);
		}

	}


	@Override
	protected ReportDateType getReportDateType() {

		return ReportDateType.WEEK;
	}
}
