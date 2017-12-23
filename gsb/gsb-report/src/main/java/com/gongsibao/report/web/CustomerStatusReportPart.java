package com.gongsibao.report.web;

import java.sql.Types;
import java.util.HashMap;

import org.netsharp.core.DataTable;
import org.netsharp.core.QueryParameters;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.report.customer.BaseCustomerReportEntity;

public class CustomerStatusReportPart extends CustomerReportPart{

	@Override
	protected HashMap<String, String>  getDate(HashMap<String, String> filterMap) {

		HashMap<String, String> dateMap= new HashMap<String, String>();
		dateMap.put("startDate", this.map.get("date>"));
		dateMap.put("endDate", this.map.get("date<"));
		return dateMap;
	}
	
	protected DataTable getDataTable(HashMap<String, String> filterMap) {

		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate");
		String endDate = dataMap.get("endDate");
		String departmentId = filterMap.get("departmentId");
		
		QueryParameters qps = new QueryParameters();
		{
			qps.add("@startDate", startDate, Types.VARCHAR);
			qps.add("@endDate", endDate, Types.VARCHAR);
		}
		
		if(!StringManager.isNullOrEmpty(departmentId)){

			qps.add("@departmentId", departmentId, Types.INTEGER);
		}
		
		
		
		
		String cmdText = "    ";
		
		DataTable dataTable = organizationService.executeTable(cmdText, qps);
		return dataTable;
	}
	
	@Override
	protected BaseCustomerReportEntity replenishEntity(BaseCustomerReportEntity entity,DataTable dataTable){
		
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
