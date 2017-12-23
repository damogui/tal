package com.gongsibao.report.web.statusReport;

import java.util.HashMap;

public class CustomerStatusReportPart extends CustomerAbstrStatusReportPart{

	@Override
	protected HashMap<String, String> getDate(HashMap<String, String> filterMap) {

		HashMap<String, String> map = new HashMap<String, String>();
		String startDate = filterMap.get("date>");
		String endDate = filterMap.get("date<");
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return map;
	}
}
