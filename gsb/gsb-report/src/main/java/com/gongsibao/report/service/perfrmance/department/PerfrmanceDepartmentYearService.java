package com.gongsibao.report.service.perfrmance.department;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.MtableManager;
import org.netsharp.core.QueryParameters;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.DeleteBuilder;
import org.netsharp.util.sqlbuilder.SelectBuilder;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.report.PerformanceStatistics;
import com.gongsibao.entity.report.dic.ReportDateType;
import com.gongsibao.entity.report.dic.ReportOrganizationType;


public class PerfrmanceDepartmentYearService extends AbstractPerfrmanceDepartmentService{


	public void doExecute() {

		SelectBuilder builder = SelectBuilder.getInstance();
		{
			builder.select("department_id as departmentId", "year", "SUM(receivable_amount) as receivableAmount", "SUM(paid_amount) as paidAmount",
					"SUM(refund_amount) as refundAmount", "SUM(net_receivables) as netReceivables", "SUM(net_paid_amount) as netPaidAmount", "SUM(product_count) as productCount",
					"SUM(order_count) as orderCount");
			builder.from(MtableManager.getMtable(PerformanceStatistics.class).getTableName());
			builder.where("year=?", "date_type=?", "organization_type=?");
			builder.groupBy("department_id");
		}

		QueryParameters qps = new QueryParameters();
		qps.add("year", this.context.getYear(), Types.INTEGER);
		qps.add("dateType", ReportDateType.SEASON.getValue(), Types.INTEGER);
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

	private PerformanceStatistics create(IRow row) {

		Integer departmentId = Integer.parseInt(row.getString("departmentId"));
		Integer year = row.getInteger("year");
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
			entity.setDateType(ReportDateType.YEAR);
			entity.setOrganizationType(ReportOrganizationType.DEPARTMENT);
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
			updateBuilder.where("year=?", "date_type=?", "organization_type=?", "department_id in (" + childIds + ")");
		}

		QueryParameters qps = new QueryParameters();
		qps.add("year", this.getContext().getYear(), Types.INTEGER);
		qps.add("dateType", ReportDateType.YEAR.getValue(), Types.INTEGER);
		qps.add("organizationType", ReportOrganizationType.DEPARTMENT.getValue(), Types.INTEGER);
		this.pm.executeNonQuery(updateBuilder.toSQL(), qps);
	}

	@Override
	public Boolean delete() {

		DeleteBuilder deleteBuilder = DeleteBuilder.getInstance();
		{
			deleteBuilder.deleteFrom(MtableManager.getMtable(PerformanceStatistics.class).getTableName());
			deleteBuilder.where("year=?", "date_type=?", "organization_type=?");
		}

		QueryParameters qps = new QueryParameters();
		{
			qps.add("year", this.getContext().getYear(), Types.INTEGER);
			qps.add("dateType", ReportDateType.YEAR.getValue(), Types.INTEGER);
			qps.add("organizationType", ReportOrganizationType.DEPARTMENT.getValue(), Types.INTEGER);
		}

		String cmdText = deleteBuilder.toSQL();
		int deleteCount = this.pm.executeNonQuery(cmdText, qps);
		return deleteCount > 0;
	}

	
	@Override
	public void before() {
		
	}
}
