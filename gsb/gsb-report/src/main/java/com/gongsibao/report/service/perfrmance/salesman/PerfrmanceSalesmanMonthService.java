package com.gongsibao.report.service.perfrmance.salesman;

import java.sql.Types;

import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.MtableManager;
import org.netsharp.core.QueryParameters;
import org.netsharp.util.sqlbuilder.SelectBuilder;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.report.PerformanceStatistics;
import com.gongsibao.entity.report.dic.ReportDateType;
import com.gongsibao.report.service.perfrmance.AbstractPerfrmanceService;

public class PerfrmanceSalesmanMonthService extends AbstractPerfrmanceSalesmanService {

	@Override
	public void before() {

		AbstractPerfrmanceService netService = new PerfrmanceSalesmanSeasonService();
		netService.setContext(context);
		this.setNextService(netService);
	}

	@Override
	public void doExecute() {

		// 生成业务员的月报表
		SelectBuilder builder = SelectBuilder.getInstance();
		{
			builder.select("department_id as departmentId", "salesman_id as salesmanId", "year", "month", "season", "SUM(receivable_amount) as receivableAmount", "SUM(paid_amount) as paidAmount",
					"SUM(refund_amount) as refundAmount", "SUM(net_receivables) as netReceivables", "SUM(net_paid_amount) as netPaidAmount", "SUM(product_count) as productCount",
					"SUM(order_count) as orderCount");
			builder.from(MtableManager.getMtable(PerformanceStatistics.class).getTableName());
			builder.where("year=?", "month=?", "date_type=?", "salesman_id is not null");
			builder.groupBy("salesman_id");
		}

		QueryParameters qps = new QueryParameters();
		qps.add("year", this.context.getYear(), Types.INTEGER);
		qps.add("month", this.context.getMonth(), Types.INTEGER);
		qps.add("dateType", ReportDateType.DAY.getValue(), Types.INTEGER);
		DataTable dataTable = this.pm.executeTable(builder.toSQL(), qps);
		for (IRow row : dataTable) {

			PerformanceStatistics entity = this.create(row);
			this.getStatisticsService().save(entity);
		}

	}

	public void updateParentId(Integer parentId, Integer salesmanId) {

		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(PerformanceStatistics.class).getTableName());
			updateBuilder.set("parent_id", parentId);
			updateBuilder.where("year=?", "month=?", "date_type=?", "salesman_id=?");
		}

		QueryParameters qps = new QueryParameters();
		qps.add("year", this.getContext().getYear(), Types.INTEGER);
		qps.add("month", this.getContext().getMonth(), Types.INTEGER);
		qps.add("dateType", ReportDateType.DAY.getValue(), Types.INTEGER);
		qps.add("salesmanId", salesmanId, Types.INTEGER);
		this.pm.executeNonQuery(updateBuilder.toSQL(), qps);
	}

	@Override
	protected ReportDateType getReportDateType() {

		return ReportDateType.MONTH;
	}
}
