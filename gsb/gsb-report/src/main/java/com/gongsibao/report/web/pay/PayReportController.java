package com.gongsibao.report.web.pay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.netsharp.core.DataTable;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.DateManage;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.Pay;

public class PayReportController {

	/**
	 * @throws ParseException
	 * @throws ParseException
	 * @Title: query
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param startDate
	 * @param: @param endDate
	 * @return: void
	 * @throws
	 */
	public Object query(Date startDate, Date endDate) throws ParseException {

		String selfsupportSql = this.getSelfsupportSql(startDate, endDate);
		String platformSql = this.getPlatformSql(startDate, endDate);
		StringBuffer buffer = new StringBuffer();
		buffer.append("(" + selfsupportSql + ")");
		buffer.append(" UNION ALL ");
		buffer.append("(" + platformSql + ")");

		String sql = buffer.toString();
		System.out.println(sql);
		IPersister<Pay> pm = PersisterFactory.create();
		DataTable table = pm.executeTable(sql, null);
		return table.getValueMapList();
	}

	/**
	 * @Title: getSql
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param startDate
	 * @param: @param endDate
	 * @param: @return
	 * @param: @throws ParseException
	 * @return: String
	 * @throws
	 */
	private String getSelfsupportSql(Date startDate, Date endDate) throws ParseException {

		// 计算需要统计的天数
		int dayCount = this.daysBetween(startDate, endDate) + 1;
		List<String> fieldList = new ArrayList<String>();
		List<String> joinList = new ArrayList<String>();
		List<String> whereList = new ArrayList<String>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT  supplier.id, supplier.name, supplier.category_id,supplier.type, ");
		for (int i = 0; i < dayCount; i++) {

			Date date = DateManage.addDays(startDate, i);
			Date nextDate = DateManage.addDays(date, 1);
			String dateStr = DateManage.toString(date);
			String nextDateStr = DateManage.toString(nextDate);
			// 审核sql
			String tableName = "auditTable_" + dateStr.replaceAll("-", "");
			fieldList.add(String.format("IFNULL(%s.auditAmount, 0) AS '%s_auditAmount'", tableName, tableName));
			String auditSql = this.getDaySql(dateStr, nextDateStr, tableName, true);
			joinList.add(auditSql);

			whereList.add(String.format(" %s.auditAmount>0 ", tableName));
		}
		buffer.append(StringManager.join(",", fieldList));
		buffer.append(" FROM sp_supplier supplier ");
		buffer.append(StringManager.join(" ", joinList));
		buffer.append(" WHERE ");// 优化点：先取出开户状态的服务商,并且是自营的
		buffer.append(" (" + StringManager.join(" OR ", whereList) + ") ");
		buffer.append(" AND supplier.id not in (1039,1041)");// 过滤测试服务商和平台事业部
		buffer.append(" and supplier.type=1");
		buffer.append(" AND supplier.STATUS = 2 ");
		buffer.append(" ORDER BY supplier.type,supplier.category_id");
		return buffer.toString();

	}

	private String getPlatformSql(Date startDate, Date endDate) throws ParseException {

		// 计算需要统计的天数
		int dayCount = this.daysBetween(startDate, endDate) + 1;
		List<String> fieldList = new ArrayList<String>();
		List<String> joinList = new ArrayList<String>();
		List<String> whereList = new ArrayList<String>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT ");
		buffer.append("IF (supplier.id <> 1041,1041,1041) AS id,");
		buffer.append("IF (supplier.NAME <> '平台事业部','平台事业部','平台事业部') AS NAME,");
		buffer.append("IF (supplier.category_id <> 439,439,439) AS category_id,");
		buffer.append("IF (supplier.type <> 1,1,1) AS category_id,");
			
		for (int i = 0; i < dayCount; i++) {

			Date date = DateManage.addDays(startDate, i);
			Date nextDate = DateManage.addDays(date, 1);
			String dateStr = DateManage.toString(date);
			String nextDateStr = DateManage.toString(nextDate);

			// 审核sql
			String tableName = "auditTable_" + dateStr.replaceAll("-", "");
			fieldList.add(String.format("sum(IFNULL(%s.auditAmount, 0)) AS '%s_auditAmount'", tableName, tableName));
			String auditSql = this.getDaySql(dateStr, nextDateStr, tableName, true);
			joinList.add(auditSql);

			whereList.add(String.format(" %s.auditAmount>0 ", tableName));
		}
		buffer.append(StringManager.join(",", fieldList));
		buffer.append(" FROM sp_supplier supplier ");
		buffer.append(StringManager.join(" ", joinList));
		buffer.append(" WHERE ");// 优化点：先取出开户状态的服务商,并且是自营的
		buffer.append(" (" + StringManager.join(" OR ", whereList) + ") ");
		buffer.append(" AND (");// 过滤测试服务商和平台事业部
		buffer.append(" (supplier.id <> 1039 AND supplier. STATUS = 2 AND supplier.type = 2 ) OR supplier.id = 1041 ");// 平台事业部,包括服务商
		buffer.append(" )");
		return buffer.toString();
	}

	/**
	 * @Title: getDaySql
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param dateStr
	 * @param: @param nextDateStr
	 * @param: @param tableName
	 * @param: @param isAudit
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	private String getDaySql(String dateStr, String nextDateStr, String tableName, boolean isAudit) {

		String asName = isAudit == true ? "auditAmount" : "submitAmount";
		StringBuffer buffer = new StringBuffer();
		buffer.append(" LEFT JOIN ( ");
		buffer.append(String.format(" SELECT so.supplier_id, SUM(map.order_price) AS %s ", asName));
		buffer.append(" FROM ( ");
		buffer.append(" SELECT order_id,order_price ");
		buffer.append(" FROM so_order_pay_map map ");
		buffer.append(" LEFT JOIN so_pay pay ON map.pay_id = pay.pkid ");
		buffer.append(" WHERE order_price > 0 ");
		buffer.append(" AND pay.success_status_id = 3123 ");
		buffer.append(" AND pay.pay_way_type_id = 3101 ");
		buffer.append(String.format(" AND pay.confirm_time >= '%s' AND pay.confirm_time < '%s' ", dateStr, nextDateStr));
		buffer.append(" UNION ALL ");
		buffer.append(" SELECT order_id, order_price ");
		buffer.append(" FROM so_order_pay_map map ");
		buffer.append(" WHERE order_price > 0 ");
		buffer.append(" AND pay_id IN ( ");
		buffer.append(" SELECT pkid FROM so_pay ");
		buffer.append(" WHERE success_status_id = 3123 ");
		buffer.append(" AND pay_way_type_id = 3102 ");
		buffer.append(String.format(" AND pay_audit_pass_time >= '%s' AND pay_audit_pass_time < '%s' ", dateStr, nextDateStr));
		buffer.append(" ) ");
		buffer.append(" ) map ");
		buffer.append(" LEFT JOIN so_order so ON map.order_id = so.pkid ");
		buffer.append(" WHERE so.supplier_id IS NOT NULL AND so.supplier_id <> 0 ");
		buffer.append(" GROUP BY so.supplier_id )");
		buffer.append(String.format("%s ON %s.supplier_id = supplier.id ", tableName, tableName));
		return buffer.toString();

	}

	/**
	 * @Title: daysBetween
	 * @Description: TODO(计算需要统计的天数)
	 * @param: @param smdate
	 * @param: @param bdate
	 * @param: @return
	 * @param: @throws ParseException
	 * @return: int
	 * @throws
	 */
	private int daysBetween(Date smdate, Date bdate) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}
}
