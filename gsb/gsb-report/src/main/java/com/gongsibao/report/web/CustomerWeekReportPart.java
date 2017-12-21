package com.gongsibao.report.web;

import org.netsharp.util.StringManager;

import com.gongsibao.entity.report.customer.BaseCustomerReportEntity;

public class CustomerWeekReportPart extends CustomerReportPart{

	@Override
	protected BaseCustomerReportEntity replenishEntity(BaseCustomerReportEntity entity){
		
		if (this.map.size() > 0) {
			
			String year = this.map.get("year");
			String week = this.map.get("week");
			if(!StringManager.isNullOrEmpty(year)){
				
				entity.setYear(Integer.parseInt(year));
			}

			if(!StringManager.isNullOrEmpty(week)){
				
				entity.setWeek(Integer.parseInt(week));
			}
		}
		return entity;
	}
}
