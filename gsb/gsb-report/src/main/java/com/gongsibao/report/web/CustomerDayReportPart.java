package com.gongsibao.report.web;

import java.util.HashMap;

import org.netsharp.core.DataTable;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.report.customer.BaseCustomerReportEntity;

public class CustomerDayReportPart extends CustomerReportPart {

	@Override
	protected HashMap<String, String> getDate(HashMap<String, String> filterMap) {

		HashMap<String, String> map= new HashMap<String, String>();
		String year = this.map.get("year");
		String month = this.map.get("month");
		String startDate = year + "-" + month + "-" + "01 00:00:00";
		String endDate = year + "-" + month + "-" + "31 59:59:59";//这里天要取每个月的最后 天，还没处理
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return map;
	}
	
	@Override
	protected BaseCustomerReportEntity replenishEntity(BaseCustomerReportEntity entity,DataTable dataTable) {

		if (this.map.size() > 0) {

			String year = this.map.get("year");
			String startDate = this.map.get("date>");
			String endDate = this.map.get("date<");
			if (!StringManager.isNullOrEmpty(year)) {

				entity.setYear(Integer.parseInt(year));
			}

			if (!StringManager.isNullOrEmpty(startDate) && !StringManager.isNullOrEmpty(endDate)) {

				String day = startDate.substring(1, 11) + " 至 " + endDate.substring(1, 11);
				entity.setDate(day);
			}
		}
		return entity;
	}
}