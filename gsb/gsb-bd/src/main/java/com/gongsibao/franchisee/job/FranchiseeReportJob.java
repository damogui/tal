package com.gongsibao.franchisee.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.job.core.IJob;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.entity.Organization;

import com.gongsibao.entity.franchisee.dic.FranchiseeReportType;
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

		// 1.先查询招商部员工信息
		IOrganizationService organizaService = ServiceFactory.create(IOrganizationService.class);
		List<Organization> getOrganList = organizaService.getByFunction("Channel");
		IFranchiseeReportService reportService = ServiceFactory.create(IFranchiseeReportService.class);
		Map<Integer, Integer> getMap = new HashMap<>();
		// 2.根据员工信息查询对应客户状态的数据，保存FranchiseeReport信息
		getMap = reportService.recursiveByOrgaId(getMap, getOrganList.get(0).getId());
		//getMap = reportService.recursiveByOrgaId(getMap, 2101204994);
		
		// 3.注意：这里需要处理上下级状态，数据展现出来是树结构
		reportService.statDireReportNo(FranchiseeReportType.date);
		reportService.statDireReportNo(FranchiseeReportType.month);
		reportService.statDireReportNo(FranchiseeReportType.year);
		// 4.注意:类，代码，方法的设计，不要堆在一起
		// 5.这里只是一个入口
	}
}
