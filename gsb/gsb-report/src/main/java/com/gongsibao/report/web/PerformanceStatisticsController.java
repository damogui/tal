package com.gongsibao.report.web;

import java.util.Date;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.TreegridPart;

import com.gongsibao.report.base.IPerformanceStatisticsService;

public class PerformanceStatisticsController extends TreegridPart{
	public void execute(Date date){
		
		IPerformanceStatisticsService service = ServiceFactory.create(IPerformanceStatisticsService.class);
		int[] departmentIds = { 337,4, 5 };// 股转中心，汉唐信通，供应商
		service.generate(date,departmentIds);
	}
}
