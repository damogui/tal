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

		String sql = this.getSql(startDate, endDate);
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
	private String getSql(Date startDate, Date endDate) throws ParseException {

		// 计算需要统计的天数
		int dayCount = this.daysBetween(startDate, endDate) + 1;
		List<String> fieldList = new ArrayList<String>();
		List<String> joinList = new ArrayList<String>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT  supplier.id, supplier.name, supplier.category_id,");
		for (int i = 0; i < dayCount; i++) {

			Date date = DateManage.addDays(startDate, i);
			Date nextDate = DateManage.addDays(date, 1);
			String dateStr = DateManage.toString(date);
			String nextDateStr = DateManage.toString(nextDate);
			String tableName = "submitTable_" + dateStr.replaceAll("-", "");
			fieldList.add(String.format("IFNULL(%s.submitAmount, 0) AS '%s_submitAmount'", tableName, tableName));

			// 提交sql
			String submitSql = this.getDaySql(dateStr, nextDateStr, tableName, false);
			joinList.add(submitSql);

			// 审核sql
			tableName = "auditTable_" + dateStr.replaceAll("-", "");
			fieldList.add(String.format("IFNULL(%s.auditAmount, 0) AS '%s_auditAmount'", tableName, tableName));
			String auditSql = this.getDaySql(dateStr, nextDateStr, tableName, true);
			joinList.add(auditSql);
		}
		buffer.append(StringManager.join(",", fieldList));
		buffer.append("   FROM sp_supplier supplier ");
		buffer.append(StringManager.join(" ", joinList));
		buffer.append("  WHERE supplier. STATUS = 2 and type=1");// 优化点：先取出开户状态的服务商,并且是自营的

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
		buffer.append(String.format(" SELECT supplier_id, SUM(amount) AS %s ", asName));
		buffer.append(" FROM so_pay ");
		buffer.append(" WHERE supplier_id IS NOT NULL ");
		if (isAudit) {
			buffer.append("  and offline_audit_status_id = 1054 ");
			buffer.append(String.format("AND pay_audit_pass_time >= '%s' AND pay_audit_pass_time < '%s' ", dateStr, nextDateStr));
		} else {
			buffer.append(String.format("AND add_time >= '%s' AND add_time < '%s' ", dateStr, nextDateStr));
		}
		buffer.append(" GROUP BY supplier_id ) ");
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
