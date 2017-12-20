package com.gongsibao.panda;

import java.util.Date;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.util.DateManage;

import com.gongsibao.franchisee.base.IFranchiseeReportService;

public class MyTest {
	@Test
	public void run() {

		IFranchiseeReportService frService = ServiceFactory.create(IFranchiseeReportService.class);

		for (int i = 10; i <= 10; i++) {

			Date date = DateManage.parse("2017-12-" + i);
			frService.generate(date);
		}
	}
}
