package com.gongsibao.report.web;

import org.netsharp.util.StringManager;

import com.gongsibao.entity.report.customer.BaseCustomerReportEntity;

public class CustomerMonthReportPart extends CustomerReportPart{

	@Override
	protected BaseCustomerReportEntity replenishEntity(BaseCustomerReportEntity entity){
		
		if (this.map.size() > 0) {
			
			String year = this.map.get("year");
			String month = this.map.get("month");
			if(!StringManager.isNullOrEmpty(year)){
				
				entity.setYear(Integer.parseInt(year));
			}

			if(!StringManager.isNullOrEmpty(month)){
				
				entity.setMonth(Integer.parseInt(month));
			}
		}
		return entity;
	}
}
