package com.gongsibao.tools.db;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.util.DateManage;

import com.gongsibao.entity.uc.UserOrganizationMap;
import com.gongsibao.uc.base.IUserOrganizationMapService;

public class HanWeiTest {

	IUserOrganizationMapService mapService = ServiceFactory.create(IUserOrganizationMapService.class);

	@Test
	public void run() {

		IPerformanceStatisticsService service = ServiceFactory.create(IPerformanceStatisticsService.class);
		
		//int[] departmentIdIds = { 4, 5 };// 汉唐信通，供应商
		int[] departmentIdIds = { 4};// 汉唐信通，供应商
		for (int i = 1; i <= 10; i++) {

			Date date =DateManage.parse("2017-05-"+i);
			//Date date = DateManage.toDate(new Date());
			service.generate(date, departmentIdIds);
		}
	}

	public void doRun(Integer departmentId) {

		List<UserOrganizationMap> mapList = mapService.getMapList(departmentId);
		System.err.println("用户数量" + mapList.size());
		for (UserOrganizationMap map : mapList) {

			System.out.println(map.getUserId() + "------" + map.getOrganizationId());
		}
	}
}
