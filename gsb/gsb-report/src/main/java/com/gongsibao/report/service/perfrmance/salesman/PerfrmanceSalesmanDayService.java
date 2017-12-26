package com.gongsibao.report.service.perfrmance.salesman;

import java.util.Date;
import java.util.List;

import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;

import com.gongsibao.entity.report.PerformanceStatistics;
import com.gongsibao.entity.report.dic.ReportDateType;
import com.gongsibao.entity.report.dic.ReportOrganizationType;
import com.gongsibao.entity.uc.UserOrganizationMap;
import com.gongsibao.report.service.perfrmance.AbstractPerfrmanceService;
import com.gongsibao.report.utils.DateUtils;

public class PerfrmanceSalesmanDayService extends AbstractPerfrmanceSalesmanService {

	@Override
	public void doExecute() {

		Date date = context.getDate();
		String strDate = DateUtils.formatDate(date);

		// 根据sql统计值
		StringBuilder sqlBuilder = new StringBuilder();
		{
			sqlBuilder.append("SELECT four.*,IFNULL(org.organization_id,0) as orgId from (");
			sqlBuilder.append("SELECT userMap.user_id as 'userId',");
			sqlBuilder.append("IFNULL(three.payTime,now()) as 'payTime',");
			sqlBuilder.append("IFNULL(SUM(three.payablePrice),0) as 'payablePrice',");
			sqlBuilder.append("IFNULL(SUM(three.getAmout),0) as 'getAmout',");
			sqlBuilder.append("IFNULL(SUM(three.refundAmount),0) as 'refundAmount',");
			sqlBuilder.append("IFNULL(SUM(three.sales),0) as 'salesCount',");
			sqlBuilder.append("IFNULL(SUM(three.orderCount),0) as 'orderCount',");
			sqlBuilder.append("IFNULL(SUM(three.payablePrice) - SUM(ifnull(three.refundAmount, 0)),0) as 'netPayablePrice',");
			sqlBuilder.append("IFNULL(SUM(three.getAmout) - SUM(ifnull(three.refundAmount, 0)),0) as 'netGetAmout'");
			sqlBuilder.append(" from so_order_prod_user_map as userMap");
			sqlBuilder.append(" LEFT JOIN (");
			sqlBuilder.append("SELECT two.orderId,two.orderCount,two.payTime,two.payablePrice,");
			sqlBuilder.append("two.refundAmount,two.getAmout,two.sales,two.prodId from (");
			sqlBuilder.append("SELECT one.orderId,one.orderCount,one.payTime,one.payablePrice");
			sqlBuilder.append(",one.refundAmount,one.getAmout,one.sales");
			sqlBuilder.append(",pr.pkid as prodId");
			sqlBuilder.append(" from (");
			sqlBuilder.append("SELECT o.pkid as orderId,");
			sqlBuilder.append("COUNT(DISTINCT o.pkid) as 'orderCount',");
			sqlBuilder.append("(SELECT COUNT(1) from so_order_prod pr where pr.order_id = o.pkid) as 'sales',");
			sqlBuilder.append("o.pay_time as 'payTime',");
			sqlBuilder.append("o.payable_price as 'payablePrice',");
			sqlBuilder.append("r.amount as 'refundAmount',");
			sqlBuilder.append("SUM((SELECT amount from so_pay where pkid in(m.pay_id))) as 'getAmout'");
			sqlBuilder.append(" from so_order o");
			sqlBuilder.append(" LEFT JOIN so_refund r on o.pkid=r.order_id");
			sqlBuilder.append(" LEFT JOIN so_order_pay_map m on o.pkid=m.order_id");
			sqlBuilder.append(" GROUP BY o.pkid HAVING o.pay_time LIKE CONCAT('%','" + strDate + "','%')");
			sqlBuilder.append(") as one");
			sqlBuilder.append(" LEFT JOIN so_order_prod as pr ON one.orderId = pr.order_id");
			sqlBuilder.append(") as two");
			sqlBuilder.append(") as three");
			sqlBuilder.append(" on three.prodId = userMap.order_prod_id");
			sqlBuilder.append(" where userMap.type_id=3061 and userMap.status_id=3141");
			sqlBuilder.append(" GROUP BY payTime,user_id");
			sqlBuilder.append(") as four");
			sqlBuilder.append(" LEFT JOIN uc_user_organization_map as org");
			sqlBuilder.append(" on org.user_id=four.userId");
		};

		DataTable dataTable = this.pm.executeTable(sqlBuilder.toString(), null);
		List<UserOrganizationMap> mapList = context.getMapList();
		for (UserOrganizationMap map : mapList) {

			Integer salesmanId = map.getUserId();
			Integer organizationId = map.getOrganizationId();
			PerformanceStatistics entity = create(context.getDate(), salesmanId, organizationId);
			for (IRow row : dataTable) {

				Integer userId = Integer.parseInt(row.getString("userId"));
				if (salesmanId.equals(userId)) {

					Integer payablePrice = Integer.parseInt(row.getString("payablePrice"));
					Integer getAmout = Integer.parseInt(row.getString("getAmout"));
					Integer refundAmount = Integer.parseInt(row.getString("refundAmount"));
					Integer salesCount = Integer.parseInt(row.getString("salesCount"));
					Integer orderCount = Integer.parseInt(row.getString("orderCount"));
					Integer netPayablePrice = Integer.parseInt(row.getString("netPayablePrice"));
					Integer netGetAmout = Integer.parseInt(row.getString("netGetAmout"));

					 entity.setReceivableAmount(payablePrice);
					 entity.setPaidAmount(getAmout);
					 entity.setRefundAmount(refundAmount);
					 entity.setNetReceivables(netPayablePrice);
					 entity.setNetPaidAmount(netGetAmout);
					 entity.setProductCount(salesCount);
					 entity.setOrderCount(orderCount);
					 this.getStatisticsService().save(entity);
				}
			}
//			int a = 1000;
//			entity.setReceivableAmount(a);
//			entity.setPaidAmount(a);
//			entity.setRefundAmount(a);
//			entity.setNetReceivables(a);
//			entity.setNetPaidAmount(a);
//			entity.setProductCount(a);
//			entity.setOrderCount(a);
//			this.getStatisticsService().save(entity);
		}

		System.out.println("ok......");
	}

	private PerformanceStatistics create(Date date, Integer salesmanId, Integer departmentId) {

		PerformanceStatistics entity = new PerformanceStatistics();
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

		return entity;
	}

	@Override
	public void before() {

		AbstractPerfrmanceService netService = new PerfrmanceSalesmanWeekService();
		netService.setContext(context);
		this.setNextService(netService);
	}

	@Override
	protected ReportDateType getReportDateType() {

		return ReportDateType.DAY;
	}
}
