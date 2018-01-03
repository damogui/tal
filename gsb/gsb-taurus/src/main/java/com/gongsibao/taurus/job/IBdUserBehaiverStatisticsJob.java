package com.gongsibao.taurus.job;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.job.core.IJob;

import com.gongsibao.taurus.base.IBdUserBehaviorStatistics;


public class IBdUserBehaiverStatisticsJob implements IJob{

	@Override
	public void execute(String par) {
		
		IBdUserBehaviorStatistics service = ServiceFactory.create(IBdUserBehaviorStatistics.class);
		service.saveJnzUserBehaviorStatistics();
	}
}
