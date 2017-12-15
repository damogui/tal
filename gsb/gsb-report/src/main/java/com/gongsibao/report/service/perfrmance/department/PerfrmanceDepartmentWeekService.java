package com.gongsibao.report.service.perfrmance.department;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.MtableManager;
import org.netsharp.core.QueryParameters;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.SelectBuilder;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.report.PerformanceStatistics;
import com.gongsibao.entity.report.dic.ReportDateType;
import com.gongsibao.entity.report.dic.ReportOrganizationType;
import com.gongsibao.report.service.perfrmance.AbstractPerfrmanceService;

public class PerfrmanceDepartmentWeekService extends AbstractPerfrmanceDepartmentService {

	public void doExecute() {

		SelectBuilder builder = SelectBuilder.getInstance();
		{
			builder.select("department_id as departmentId", "year", "week", "SUM(receivable_amount) as receivableAmount", "SUM(paid_amount) as paidAmount",
					"SUM(refund_amount) as refundAmount", "SUM(net_receivables) as netReceivables", "SUM(net_paid_amount) as netPaidAmount", "SUM(product_count) as productCount",
					"SUM(order_count) as orderCount");
			builder.from(MtableManager.getMtable(PerformanceStatistics.class).getTableName());
			builder.where("year=?", "week=?", "date_type=?", "organization_type=?");
			builder.groupBy("department_id");
		}

		QueryParameters qps = new QueryParameters();
		qps.add("year", this.context.getYear(), Types.INTEGER);
		qps.add("week", this.context.getWeek(), Types.DATE);
		qps.add("dateType", ReportDateType.DAY.getValue(), Types.INTEGER);
		qps.add("organizationType", ReportOrganizationType.DEPARTMENT.getValue(), Types.INTEGER);
		DataTable dataTable = this.pm.executeTable(builder.toSQL(), qps);

		List<PerformanceStatistics> list = new ArrayList<PerformanceStatistics>();
		for (IRow row : dataTable) {

			PerformanceStatistics entity = this.create(row);
			list.add(entity);
		}

		for (PerformanceStatistics entity : list) {

			updateParentId(entity);
		}
	}

	public void updateParentId(PerformanceStatistics entity) {

		List<Integer> childDepartmentIdList = getChildDepartmentIdList(entity.getDepartmentId());
		if (childDepartmentIdList.size() == 0) {

			return;
		}

		String childIds = StringManager.join(",", childDepartmentIdList);
		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(PerformanceStatistics.class).getTableName());
			updateBuilder.set("parent_id", entity.getId());
			updateBuilder.where("year=?", "week=?", "date_type=?", "organization_type=?", "department_id in (" + childIds + ")");
		}

		QueryParameters qps = new QueryParameters();
		qps.add("year", this.getContext().getYear(), Types.INTEGER);
		qps.add("week", this.getContext().getWeek(), Types.INTEGER);
		qps.add("dateType", ReportDateType.WEEK.getValue(), Types.INTEGER);
		qps.add("organizationType", ReportOrganizationType.DEPARTMENT.getValue(), Types.INTEGER);
		this.pm.executeNonQuery(updateBuilder.toSQL(), qps);
	}

	@Override
	public void before() {

		AbstractPerfrmanceService netService = new PerfrmanceDepartmentMonthService();
		netService.setContext(context);
		this.setNextService(netService);
	}

	@Override
	public ReportDateType getReportDateType() {
		return ReportDateType.WEEK;
	}
}
