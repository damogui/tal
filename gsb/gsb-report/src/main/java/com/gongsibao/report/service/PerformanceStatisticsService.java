package com.gongsibao.report.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.report.PerformanceStatistics;
import com.gongsibao.report.base.IPerformanceStatisticsService;

@Service
public class PerformanceStatisticsService extends PersistableService<PerformanceStatistics> implements IPerformanceStatisticsService {

	public PerformanceStatisticsService() {
		super();
		this.type = PerformanceStatistics.class;
	}
}