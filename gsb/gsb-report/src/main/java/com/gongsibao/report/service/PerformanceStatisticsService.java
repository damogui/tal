package com.gongsibao.report.service;

import java.util.Date;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.report.PerformanceStatistics;
import com.gongsibao.report.base.IPerformanceStatisticsService;
import com.gongsibao.report.service.perfrmance.salesman.PerfrmanceSalesmanDayService;
import com.gongsibao.uc.base.IUserService;

@Service
public class PerformanceStatisticsService extends PersistableService<PerformanceStatistics> implements IPerformanceStatisticsService {

	public PerformanceStatisticsService() {
		super();
		this.type = PerformanceStatistics.class;
	}

	@Override
	public Boolean generate(Date date) {

		PerfrmanceSalesmanDayService generateService = new PerfrmanceSalesmanDayService();
		IUserService userService = ServiceFactory.create(IUserService.class);
		int[] departmentIdIds = { 4, 5 };//汉唐信通，供应商
		for (int i = 0; i < departmentIdIds.length; i++) {

			int departmentId = departmentIdIds[i];
			List<Integer> userIdList = userService.getIdList(departmentId);
			for (Integer userId : userIdList) {

				generateService.execute(userId, date);
			}
		}

		return true;
	}
}