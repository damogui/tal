package com.gongsibao.report.web.dataReport;

import java.util.Date;
import java.util.HashMap;

import org.netsharp.core.DataTable;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.report.customer.BaseCustomerReportEntity;
import com.gongsibao.utils.DateUtils;

public class CustomerMonthReportPart extends CustomerReportPart {

	@Override
	protected HashMap<String, String>  getDate(HashMap<String, String> filterMap) {

		HashMap<String, String> map= new HashMap<String, String>();
		String year = this.map.get("year");
		String month = this.map.get("month");
		String startDate = year + "-" + month + "-" + "01 00:00:00";
		
		String endDate = year + "-" + month + "-" + "01 59:59:59";//这里天要取每个月的最后 天
		Date date = DateUtils.getLastDateOfMonth(DateUtils.parseDate(endDate));
		endDate = DateUtils.formatDate(date, "yyyy-MM-dd HH:mm:ss");
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return map;
	}

	@Override
	protected BaseCustomerReportEntity replenishEntity(BaseCustomerReportEntity entity, DataTable dataTable) {

		if (this.map.size() > 0) {

			String year = this.map.get("year");
			String month = this.map.get("month");
			if (!StringManager.isNullOrEmpty(year)) {

				entity.setYear(Integer.parseInt(year));
			}

			if (!StringManager.isNullOrEmpty(month)) {

				entity.setMonth(Integer.parseInt(month));
			}
		}
		return entity;
	}
}
