package com.gongsibao.report.job;

import java.util.Calendar;
import java.util.Date;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.job.core.IJob;

import com.gongsibao.report.base.IPerformanceStatisticsService;

public class IPerfrmanceJob implements IJob{

	@Override
	public void execute(String par) {
		
		IPerformanceStatisticsService service = ServiceFactory.create(IPerformanceStatisticsService.class);
		//跑批的前一天时间
		Date date = new Date();
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        date = calendar.getTime();
		int[] departmentIdIds = { 4, 5 };//汉唐信通，供应商
		service.generate(date,departmentIdIds);
	}

}
