package com.gongsibao.tools.db;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.util.DateManage;

import com.gongsibao.entity.uc.Organization;
import com.gongsibao.report.base.IPerformanceStatisticsService;
import com.gongsibao.uc.base.IOrganizationService;
import com.gongsibao.uc.base.IUserOrganizationMapService;

public class HanWeiTest {

	IUserOrganizationMapService mapService = ServiceFactory.create(IUserOrganizationMapService.class);

	@Test
	public void run() {

		//dddd();
		
		doRun();
	}
	
	private void dddd(){
		
		IOrganizationService service = ServiceFactory.create(IOrganizationService.class);
		List<Organization> list = service.getChildList(4);
//		List<Integer> list = service.getLeafIdList(4);
		System.err.println(list.size());
	}

	public void doRun() {

		IPerformanceStatisticsService service = ServiceFactory.create(IPerformanceStatisticsService.class);
		
		//int[] departmentIdIds = { 4, 5 };// 汉唐信通，供应商
		int[] departmentIdIds = { 4,5};// 汉唐信通，供应商
		for (int i = 1; i <= 1; i++) {

			Date date =DateManage.parse("2017-12-26");
			//Date date = DateManage.toDate(new Date());
			service.generate(date, departmentIdIds);
		}
	}
}
