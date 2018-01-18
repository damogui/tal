package com.gongsibao.report.service.perfrmance;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.IRow;
import org.netsharp.core.MtableManager;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.DeleteBuilder;

import com.gongsibao.entity.report.PerformanceStatistics;
import com.gongsibao.entity.report.dic.ReportDateType;
import com.gongsibao.entity.report.dic.ReportOrganizationType;
import com.gongsibao.report.base.IPerformanceStatisticsService;

public abstract class AbstractPerfrmanceService {

	protected IPersister<PerformanceStatistics> pm = PersisterFactory.create();

	protected IPerformanceStatisticsService statisticsService;

	/**
	 * @Fields context : TODO(报表统计上下文)
	 */
	protected PerfrmanceContext context;

	/**
	 * @Fields nextService : TODO(下一个统计服务)
	 */
	protected AbstractPerfrmanceService nextService;

	protected abstract ReportDateType getReportDateType();

	protected abstract ReportOrganizationType getReportOrganizationType();

	/**
	 * @Title: execute
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param principalId 部门Id，业务员Id
	 * @param: @param date 统计日期
	 * @return: void
	 * @throws
	 */
	public void execute() {

		this.before();

		this.delete();

		this.doExecute();

		if (this.nextService != null) {

			this.nextService.execute();
		}
	}

	/**
	 * @Title: before
	 * @Description: TODO(补全)
	 * @param:
	 * @return: void
	 * @throws
	 */
	public abstract void before();

	/**
	 * @Title: delete
	 * @Description: TODO(删除)
	 * @param:
	 * @return: void
	 * @throws
	 */
	public Boolean delete() {

		List<String> filterList = new ArrayList<String>();
		filterList.add("date_type=?");
		filterList.add("organization_type=?");
		filterList.add("year=?");
		QueryParameters qps = new QueryParameters();
		{
			qps.add("dateType", this.getReportDateType().getValue(), Types.INTEGER);
			qps.add("organizationType", this.getReportOrganizationType().getValue(), Types.INTEGER);
			qps.add("year", this.getContext().getYear(), Types.INTEGER);
		}

		if (this.getReportDateType() == ReportDateType.DAY) {

			filterList.add("month=?");
			filterList.add("date=?");
			
			qps.add("month", this.getContext().getMonth(), Types.INTEGER);
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			qps.add("date", formatter.format(this.context.getDate()), Types.DATE);
			
		} else if (this.getReportDateType() == ReportDateType.WEEK) {

			filterList.add("week=?");
			qps.add("week", this.getContext().getWeek(), Types.INTEGER);
			
		} else if (this.getReportDateType() == ReportDateType.MONTH) {

			filterList.add("month=?");
			qps.add("month", this.getContext().getMonth(), Types.INTEGER);
			
		} else if (this.getReportDateType() == ReportDateType.SEASON) {
			
			filterList.add("season=?");
			qps.add("season", this.getContext().getSeason(), Types.INTEGER);
			
		} else if (this.getReportDateType() == ReportDateType.YEAR) {

		}

		String whereSql = StringManager.join(" and ",filterList);
		DeleteBuilder deleteBuilder = DeleteBuilder.getInstance();
		{
			deleteBuilder.deleteFrom(MtableManager.getMtable(PerformanceStatistics.class).getTableName());
			deleteBuilder.where(whereSql);
		}
		
		String cmdText = deleteBuilder.toSQL();
		int deleteCount = this.pm.executeNonQuery(cmdText, qps);
		return deleteCount > 0;
	}
	
	
	protected PerformanceStatistics create(IRow row) {

		Integer departmentId = Integer.parseInt(row.getString("departmentId"));
		Integer salesmanId = row.getInteger("salesmanId");
		Integer year = row.getInteger("year");
		Integer season = row.getInteger("season");
		Integer week = row.getInteger("week");
		Integer month = row.getInteger("month");
		Integer day = row.getInteger("day");
		Date date = row.getDate("date");
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
			entity.setDateType(getReportDateType());
			entity.setOrganizationType(getReportOrganizationType());
			entity.setSeason(season);
			entity.setMonth(month);
			entity.setYear(year);
			entity.setWeek(week);
			entity.setDay(day);
			entity.setDate(date);
			entity.setReceivableAmount(receivableAmount);
			entity.setPaidAmount(paidAmount);
			entity.setRefundAmount(refundAmount);
			entity.setNetReceivables(netReceivables);
			entity.setNetPaidAmount(netPaidAmount);
			entity.setProductCount(productCount);
			entity.setOrderCount(orderCount);
		}
		return entity;
	}

	public abstract void doExecute();

	public PerfrmanceContext getContext() {
		return context;
	}

	public void setContext(PerfrmanceContext context) {
		this.context = context;
	}

	public AbstractPerfrmanceService getNextService() {
		return nextService;
	}

	public void setNextService(AbstractPerfrmanceService nextService) {
		this.nextService = nextService;
	}

	public IPerformanceStatisticsService getStatisticsService() {

		if (statisticsService == null) {

			statisticsService = ServiceFactory.create(IPerformanceStatisticsService.class);
		}
		return statisticsService;
	}
}
