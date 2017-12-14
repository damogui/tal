package com.gongsibao.report.job;

import java.util.Date;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.job.core.IJob;

import com.gongsibao.report.base.IPerformanceStatisticsService;

public class IPerfrmanceJob implements IJob{

	@Override
	public void execute(String par) {
		
		IPerformanceStatisticsService service = ServiceFactory.create(IPerformanceStatisticsService.class);
		Date date = new Date();
		service.generate(date);
	}

}
