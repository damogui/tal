package com.gongsibao.report.service.perfrmance.department;

import java.sql.Types;
import java.util.Date;

import org.netsharp.core.MtableManager;
import org.netsharp.core.QueryParameters;
import org.netsharp.util.sqlbuilder.DeleteBuilder;

import com.gongsibao.entity.report.PerformanceStatistics;
import com.gongsibao.entity.report.dic.ReportDateType;
import com.gongsibao.entity.report.dic.ReportOrganizationType;
import com.gongsibao.report.service.perfrmance.AbstractPerfrmanceService;
import com.gongsibao.report.utils.DateUtils;

public class PerfrmanceDepartmentMonthService extends AbstractPerfrmanceService{

	@Override
	public void doExecute() {

	}
	
	private PerformanceStatistics create(Date date, Integer salesmanId, Integer departmentId) {

		PerformanceStatistics entity = new PerformanceStatistics();
		{
			entity.toNew();
			entity.setDepartmentId(departmentId);
			entity.setSalesmanId(salesmanId);
			entity.setDateType(ReportDateType.MONTH);
			entity.setOrganizationType(ReportOrganizationType.SALESMAN);
			entity.setWeek(DateUtils.getWeekOfYear(date));
			entity.setSeason(DateUtils.getSeason(date));
			entity.setMonth(DateUtils.getMonth(date));
			entity.setYear(DateUtils.getYear(date));
			entity.setDay(DateUtils.getPassDayOfMonth(date));
			entity.setDate(this.context.getDate());
		}
		entity = this.getStatisticsService().save(entity);
		return entity;
	}
	
	@Override
	public void before() {
		
		AbstractPerfrmanceService netService = new PerfrmanceDepartmentSeasonService();
		netService.setContext(context);
		this.setNextService(netService);
	}

	@Override
	public Boolean delete() {

		String ids = this.context.getSalesmanIds();
		DeleteBuilder deleteBuilder = DeleteBuilder.getInstance();
		{
			deleteBuilder.deleteFrom(MtableManager.getMtable(PerformanceStatistics.class).getTableName());
			deleteBuilder.where("date=?","date_type=?","organization_type=?", "salesman_id in (" + ids + ")");
		}
		QueryParameters qps = new QueryParameters();
		qps.add("date",  this.context.getDate(), Types.DATE);
		qps.add("day", ReportDateType.MONTH.getValue(), Types.INTEGER);
		qps.add("organizationType",  ReportOrganizationType.SALESMAN.getValue(), Types.INTEGER);
		String cmdText = deleteBuilder.toSQL();
		return this.pm.executeNonQuery(cmdText, qps) > 0;
	}
}
