package com.gongsibao.report.web;

import java.util.Date;

import org.netsharp.panda.commerce.TreegridPart;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.crm.test.ImportOldDataToNewData;

public class PerformanceStatisticsController extends TreegridPart {
	public void execute(Date date) {

		// IPerformanceStatisticsService service =
		// ServiceFactory.create(IPerformanceStatisticsService.class);
		// int[] departmentIds = { 337,4, 5 };// 股转中心，汉唐信通，供应商
		// service.generate(date,departmentIds);

		
		//临时使用
		if (SessionManager.getUserName().equals("韩伟")) {

			ImportOldDataToNewData test = new ImportOldDataToNewData();
			test.run();
		}
	}
}
