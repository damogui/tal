package com.gongsibao.report.web.dataReport;

import java.util.HashMap;

import org.netsharp.core.DataTable;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.report.customer.BaseCustomerReportEntity;

public class CustomerYearReportPart extends CustomerReportPart{

	@Override
	protected HashMap<String, String> getDate(HashMap<String, String> filterMap) {

		HashMap<String, String> map= new HashMap<String, String>();
		String year = this.map.get("year");
		String startDate = year + "-01-" + "01 00:00:00";
		String endDate = year + "-12-" + "31 59:59:59";
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return map;
	}
	
	@Override
	protected BaseCustomerReportEntity replenishEntity(BaseCustomerReportEntity entity,DataTable dataTable){
		
		if (this.map.size() > 0) {
			
			String year = this.map.get("year");
			if(!StringManager.isNullOrEmpty(year)){
				
				entity.setYear(Integer.parseInt(year));
			}
		}
		return entity;
	}
	
}
