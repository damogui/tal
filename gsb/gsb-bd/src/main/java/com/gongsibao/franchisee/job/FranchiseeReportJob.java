package com.gongsibao.franchisee.job;

import java.util.Date;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.job.core.IJob;

import com.gongsibao.franchisee.base.IFranchiseeReportService;

/**
 * @ClassName: FranchiseeReportJob
 * @Description:TODO 招商CRM统计报表定时任务
 * @author: 韩伟
 * @date: 2017年12月3日 下午8:06:42
 * 
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved.
 */
public class FranchiseeReportJob implements IJob {

	@Override
	public void execute(String par) {

		IFranchiseeReportService service = ServiceFactory.create(IFranchiseeReportService.class);
		Date date = new Date();
		service.execute(date);
	}
}
