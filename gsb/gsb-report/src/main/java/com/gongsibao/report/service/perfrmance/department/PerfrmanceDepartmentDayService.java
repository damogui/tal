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
import com.gongsibao.report.service.perfrmance.AbstractPerfrmanceService;

public class PerfrmanceDepartmentDayService extends AbstractPerfrmanceDepartmentService {

	public void doExecute() {

		SelectBuilder builder = SelectBuilder.getInstance();
		{
			builder.select("department_id as departmentId", "year", "month", "season", "week", "date", "SUM(receivable_amount) as receivableAmount", "SUM(paid_amount) as paidAmount",
					"SUM(refund_amount) as refundAmount", "SUM(net_receivables) as netReceivables", "SUM(net_paid_amount) as netPaidAmount", "SUM(product_count) as productCount",
					"SUM(order_count) as orderCount");
			builder.from(MtableManager.getMtable(PerformanceStatistics.class).getTableName());
			builder.where("date=?", "date_type=?", "organization_type=?");
			builder.groupBy("department_id");
		}

		QueryParameters qps = new QueryParameters();
		qps.add("date", this.context.getDate(), Types.DATE);
		qps.add("dateType", ReportDateType.DAY.getValue(), Types.INTEGER);
		qps.add("organizationType", ReportOrganizationType.SALESMAN.getValue(), Types.INTEGER);
		DataTable dataTable = this.pm.executeTable(builder.toSQL(), qps);

		List<Integer> departmentIdList = new ArrayList<Integer>();
		for (IRow row : dataTable) {

			Integer departmentId = row.getInteger("departmentId");
			departmentIdList.add(departmentId);
			this.create(row);
		}

		createParentDepartment(departmentIdList);
	}

	/**
	 * @Title: createParentDepartment
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param departmentIdList 子部门Id集合
	 * @return: void
	 * @throws
	 */
	private void createParentDepartment(List<Integer> departmentIdList) {

		List<Integer> parentIdList = getParentDepartmentIdList(departmentIdList);
		if (parentIdList.size() == 0) {

			return;
		}

		for (Integer parentId : parentIdList) {

			List<Integer> childDepartmentIdList = getChildDepartmentIdList(parentId);
			String childIds = StringManager.join(",", childDepartmentIdList);
			SelectBuilder builder = SelectBuilder.getInstance();
			{
				builder.select("'" + parentId + "' as departmentId", "year", "month", "season", "week", "date", "SUM(receivable_amount) as receivableAmount", "SUM(paid_amount) as paidAmount",
						"SUM(refund_amount) as refundAmount", "SUM(net_receivables) as netReceivables", "SUM(net_paid_amount) as netPaidAmount", "SUM(product_count) as productCount",
						"SUM(order_count) as orderCount");
				builder.from(MtableManager.getMtable(PerformanceStatistics.class).getTableName());
				builder.where("date=?", "date_type=?", "organization_type=?", "department_id in (" + childIds + ")");
				// builder.groupBy("department_id");
			}

			QueryParameters qps = new QueryParameters();
			qps.add("date", this.context.getDate(), Types.DATE);
			qps.add("dateType", ReportDateType.DAY.getValue(), Types.INTEGER);
			qps.add("organizationType", ReportOrganizationType.DEPARTMENT.getValue(), Types.INTEGER);
			DataTable dataTable = this.pm.executeTable(builder.toSQL(), qps);
			for (IRow row : dataTable) {

				PerformanceStatistics entity = this.create(row);
				this.updateParentId(entity.getId(), childIds);
			}
		}

		// 递归创建上级部门Id
		createParentDepartment(parentIdList);
	}

	private PerformanceStatistics create(IRow row) {

		Integer departmentId = Integer.parseInt(row.getString("departmentId"));
		Integer year = row.getInteger("year");
		Integer season = row.getInteger("season");
		Integer week = row.getInteger("week");
		Integer month = row.getInteger("month");
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
			entity.setDateType(ReportDateType.DAY);
			entity.setOrganizationType(ReportOrganizationType.DEPARTMENT);
			entity.setSeason(season);
			entity.setMonth(month);
			entity.setYear(year);
			entity.setWeek(week);
			entity.setDay(this.context.getDay());
			entity.setDate(this.context.getDate());
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

	public void updateParentId(Integer parentId, String childDepartmentIds) {

		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(PerformanceStatistics.class).getTableName());
			updateBuilder.set("parent_id", parentId);
			updateBuilder.where("date=?", "date_type=?", "organization_type=?", "department_id in (" + childDepartmentIds + ")");
		}

		QueryParameters qps = new QueryParameters();
		qps.add("date", this.getContext().getDate(), Types.DATE);
		qps.add("dateType", ReportDateType.DAY.getValue(), Types.INTEGER);
		qps.add("organizationType", ReportOrganizationType.DEPARTMENT.getValue(), Types.INTEGER);
		this.pm.executeNonQuery(updateBuilder.toSQL(), qps);
	}

	@Override
	public void before() {
		AbstractPerfrmanceService netService = new PerfrmanceDepartmentWeekService();
		netService.setContext(context);
		this.setNextService(netService);
	}

	@Override
	public Boolean delete() {

		DeleteBuilder deleteBuilder = DeleteBuilder.getInstance();
		{
			deleteBuilder.deleteFrom(MtableManager.getMtable(PerformanceStatistics.class).getTableName());
			deleteBuilder.where("year=?", "month=?", "date=?", "date_type=?", "organization_type=?");
		}

		QueryParameters qps = new QueryParameters();
		{
			qps.add("year", this.getContext().getYear(), Types.INTEGER);
			qps.add("month", this.getContext().getMonth(), Types.INTEGER);
			qps.add("date", this.context.getDate(), Types.DATE);
			qps.add("dateType", ReportDateType.DAY.getValue(), Types.INTEGER);
			qps.add("organizationType", ReportOrganizationType.DEPARTMENT.getValue(), Types.INTEGER);
		}

		String cmdText = deleteBuilder.toSQL();
		int deleteCount = this.pm.executeNonQuery(cmdText, qps);
		return deleteCount > 0;
	}

}
