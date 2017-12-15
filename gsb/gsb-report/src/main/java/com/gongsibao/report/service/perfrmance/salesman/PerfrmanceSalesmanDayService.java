package com.gongsibao.report.service.perfrmance.salesman;

import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.netsharp.core.MtableManager;
import org.netsharp.core.QueryParameters;
import org.netsharp.util.sqlbuilder.DeleteBuilder;

import com.gongsibao.entity.report.PerformanceStatistics;
import com.gongsibao.entity.report.dic.ReportDateType;
import com.gongsibao.entity.report.dic.ReportOrganizationType;
import com.gongsibao.entity.uc.UserOrganizationMap;
import com.gongsibao.report.service.perfrmance.AbstractPerfrmanceService;

public class PerfrmanceSalesmanDayService extends AbstractPerfrmanceService {

	@Override
	public void doExecute() {

		Date date = context.getDate();
		List<UserOrganizationMap> mapList = context.getMapList();
		for (UserOrganizationMap map : mapList) {

			this.create(date, map.getUserId(), map.getOrganizationId());
		}

		// 要根据sql统计重新计算值
	}

	private PerformanceStatistics create(Date date, Integer salesmanId, Integer departmentId) {

		PerformanceStatistics entity = new PerformanceStatistics();
		{
			entity.toNew();
			entity.setDepartmentId(departmentId);
			entity.setSalesmanId(salesmanId);
			entity.setDateType(ReportDateType.DAY);
			entity.setOrganizationType(ReportOrganizationType.SALESMAN);
			entity.setWeek(this.context.getWeek());
			entity.setSeason(this.context.getSeason());
			entity.setMonth(this.context.getMonth());
			entity.setYear(this.context.getYear());
			entity.setDay(this.context.getDay());
			entity.setDate(this.context.getDate());

			entity.setReceivableAmount(10);
			entity.setPaidAmount(10);
			entity.setRefundAmount(10);
			entity.setNetReceivables(10);
			entity.setNetPaidAmount(10);
			entity.setProductCount(10);
			entity.setOrderCount(10);

		}
		entity = this.getStatisticsService().save(entity);
		return entity;
	}

	@Override
	public void before() {

		AbstractPerfrmanceService netService = new PerfrmanceSalesmanWeekService();
		netService.setContext(context);
		this.setNextService(netService);
	}

	@Override
	public Boolean delete() {

		// 先删除当天的记录
		String ids = this.context.getSalesmanIds();
		DeleteBuilder deleteBuilder = DeleteBuilder.getInstance();
		{
			deleteBuilder.deleteFrom(MtableManager.getMtable(PerformanceStatistics.class).getTableName());
			deleteBuilder.where("year=?", "month=?", "date=?", "date_type=?", "organization_type=?", "salesman_id in (" + ids + ")");
		}
		QueryParameters qps = new QueryParameters();
		{
			qps.add("year", this.getContext().getYear(), Types.INTEGER);
			qps.add("month", this.getContext().getMonth(), Types.INTEGER);
			qps.add("date", this.context.getDate(), Types.DATE);
			qps.add("dateType", ReportDateType.DAY.getValue(), Types.INTEGER);
			qps.add("organizationType", ReportOrganizationType.SALESMAN.getValue(), Types.INTEGER);
		}
		String cmdText = deleteBuilder.toSQL();
		return this.pm.executeNonQuery(cmdText, qps) > 0;
	}
}
