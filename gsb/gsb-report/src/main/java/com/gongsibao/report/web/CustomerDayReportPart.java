package com.gongsibao.report.web;

import org.netsharp.util.StringManager;

import com.gongsibao.entity.report.customer.BaseCustomerReportEntity;

public class CustomerDayReportPart extends CustomerReportPart {

	@Override
	protected BaseCustomerReportEntity replenishEntity(BaseCustomerReportEntity entity) {

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