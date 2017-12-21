package com.gongsibao.report.web;

import java.util.HashMap;
import java.util.List;

import org.netsharp.core.DataTable;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.report.customer.BaseCustomerReportEntity;

public class CustomerMonthReportPart extends CustomerReportPart{

	@Override
	protected List<String> getDate(HashMap<String, String> filterMap) {

		return null;
	}
	
	@Override
	protected BaseCustomerReportEntity replenishEntity(BaseCustomerReportEntity entity,DataTable dataTable){
		
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
