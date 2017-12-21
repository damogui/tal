package com.gongsibao.report.web;

import org.netsharp.util.StringManager;

import com.gongsibao.entity.report.customer.BaseCustomerReportEntity;

public class CustomerYearReportPart extends CustomerReportPart{

	
	@Override
	protected BaseCustomerReportEntity replenishEntity(BaseCustomerReportEntity entity){
		
		if (this.map.size() > 0) {
			
			String year = this.map.get("year");
			if(!StringManager.isNullOrEmpty(year)){
				
				entity.setYear(Integer.parseInt(year));
			}
		}
		return entity;
	}
	
}
