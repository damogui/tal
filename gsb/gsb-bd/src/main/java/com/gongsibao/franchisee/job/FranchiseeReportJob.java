package com.gongsibao.franchisee.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.job.core.IJob;

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

		// ====1.先查询招商部员工信息
		
		//临时部门假数据
		Map<Integer, List<Integer>> tempDepartmentMap=new HashMap<Integer, List<Integer>>();
		List<Integer>getOne=new ArrayList<Integer>();
		getOne.add(1093);
		getOne.add(1094);
		getOne.add(1459);
		getOne.add(1460);
		getOne.add(1711);
		getOne.add(1712);
		getOne.add(1721);
		tempDepartmentMap.put(6, getOne);
		List<Integer>getTwo=new ArrayList<Integer>();
		getTwo.add(120);
		getTwo.add(1076);
		getTwo.add(1722);
		tempDepartmentMap.put(1, getTwo);
		
		// 报表接口
		IFranchiseeReportService reportService = ServiceFactory.create(IFranchiseeReportService.class);
		reportService.createDayReport(tempDepartmentMap);
		reportService.createYearMonthReport(tempDepartmentMap,FranchiseeReportType.month);
		reportService.createYearMonthReport(tempDepartmentMap,FranchiseeReportType.year);
		// 2.根据员工信息查询对应客户状态的数据，保存FranchiseeReport信息

		// 3.注意：这里需要处理上下级状态，数据展现出来是树结构
		// 4.注意:类，代码，方法的设计，不要堆在一起
		// 5.这里只是一个入口
	}
}
