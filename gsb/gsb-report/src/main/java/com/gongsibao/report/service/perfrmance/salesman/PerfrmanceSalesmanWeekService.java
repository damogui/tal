package com.gongsibao.report.service.perfrmance.salesman;

import java.sql.Types;

import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.MtableManager;
import org.netsharp.core.QueryParameters;
import org.netsharp.util.sqlbuilder.DeleteBuilder;
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
public class PerfrmanceSalesmanWeekService extends AbstractPerfrmanceService {

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
			builder.select("department_id as departmentId", "salesman_id as salesmanId", "year", "week",  "month","season", "SUM(receivable_amount) as receivableAmount", "SUM(paid_amount) as paidAmount",
					"SUM(refund_amount) as refundAmount", "SUM(net_receivables) as netReceivables", "SUM(net_paid_amount) as netPaidAmount", "SUM(product_count) as productCount",
					"SUM(order_count) as orderCount");
			builder.from(MtableManager.getMtable(PerformanceStatistics.class).getTableName());
			builder.where("year=?", "week=?", "date_type=?");
			builder.groupBy("salesman_id");
		}

		QueryParameters qps = new QueryParameters();
		qps.add("year", this.context.getYear(), Types.INTEGER);
		qps.add("week", this.context.getWeek(), Types.INTEGER);
		qps.add("dateType", ReportDateType.DAY.getValue(), Types.INTEGER);
		DataTable dataTable = this.pm.executeTable(builder.toSQL(), qps);
		for (IRow row : dataTable) {

			this.create(row);
		}

	}

	private PerformanceStatistics create(IRow row) {

		Integer departmentId = row.getInteger("departmentId");
		Integer salesmanId = row.getInteger("salesmanId");
		Integer year = row.getInteger("year");
		Integer week = row.getInteger("week");
		Integer receivableAmount = Integer.parseInt(row.getString("receivableAmount"));
		Integer paidAmount = Integer.parseInt(row.getString("paidAmount"));
		Integer refundAmount = Integer.parseInt(row.getString("refundAmount"));
		Integer netReceivables = Integer.parseInt(row.getString("netReceivables"));
		Integer netPaidAmount = Integer.parseInt(row.getString("netPaidAmount"));
		Integer productCount = Integer.parseInt(row.getString("productCount"));
		Integer orderCount = Integer.parseInt(row.getString("orderCount"));
		PerformanceStatistics entity = new PerformanceStatistics();
		{
			entity.toNew();
			entity.setDepartmentId(departmentId);
			entity.setSalesmanId(salesmanId);
			entity.setDateType(ReportDateType.WEEK);
			entity.setOrganizationType(ReportOrganizationType.SALESMAN);
			entity.setWeek(week);
			entity.setYear(year);

			entity.setReceivableAmount(receivableAmount);
			entity.setPaidAmount(paidAmount);
			entity.setRefundAmount(refundAmount);
			entity.setNetReceivables(netReceivables);
			entity.setNetPaidAmount(netPaidAmount);
			entity.setProductCount(productCount);
			entity.setOrderCount(orderCount);
		}
		entity = this.getStatisticsService().save(entity);
		return entity;
	}

	@Override
	public Boolean delete() {

		String ids = this.context.getSalesmanIds();
		DeleteBuilder deleteBuilder = DeleteBuilder.getInstance();
		{
			deleteBuilder.deleteFrom(MtableManager.getMtable(PerformanceStatistics.class).getTableName());
			deleteBuilder.where("year=?", "week=?", "date_type=?", "organization_type=?", "salesman_id in (" + ids + ")");
		}

		QueryParameters qps = new QueryParameters();
		{
			qps.add("year", this.getContext().getYear(), Types.INTEGER);
			qps.add("week", this.getContext().getWeek(), Types.INTEGER);
			qps.add("dateType", ReportDateType.WEEK.getValue(), Types.INTEGER);
			qps.add("organizationType", ReportOrganizationType.SALESMAN.getValue(), Types.INTEGER);
		}

		String cmdText = deleteBuilder.toSQL();
		return this.pm.executeNonQuery(cmdText, qps) > 0;
	}
}
