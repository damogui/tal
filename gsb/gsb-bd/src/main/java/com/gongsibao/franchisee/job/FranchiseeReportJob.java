package com.gongsibao.franchisee.job;

import java.util.Calendar;
import java.util.Map;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.job.core.IJob;

import com.gongsibao.entity.franchisee.FranchiseeReport;
import com.gongsibao.entity.franchisee.dic.FranchiseeReportType;
import com.gongsibao.franchisee.base.IFranchiseeReportService;
import com.gongsibao.franchisee.base.IFranchiseeService;

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
		Integer departId = 6;
		Integer[] employeeId = { 1093, 1094, 1459, 1460, 1711, 1712, 1721 };

		// 获取当前时间
		Calendar cal = Calendar.getInstance();// 使用日历类
		int year = cal.get(Calendar.YEAR);// 得到年
		int month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
		int day = cal.get(Calendar.DAY_OF_MONTH);// 得到天
		int hour = cal.get(Calendar.HOUR);// 得到小时
		int minute = cal.get(Calendar.MINUTE);// 得到分钟
		int second = cal.get(Calendar.SECOND);// 得到秒
		String getCurrentTimeString = year + "-" + month + "-" + day + " "
				+ hour + ":" + minute + ":" + second;

		// 供应商接口
		IFranchiseeService franchService = ServiceFactory
				.create(IFranchiseeService.class);
		// 报表接口
		IFranchiseeReportService reportService = ServiceFactory
				.create(IFranchiseeReportService.class);

		for (Integer item : employeeId) {
			Map<Integer, Integer> getMap = franchService.getCustomersAllTotal(
					item, getCurrentTimeString);
			if (getMap.isEmpty()) {
				FranchiseeReport entity = new FranchiseeReport();
				entity.setOrganizationId(departId);
				entity.setOwnerId(item);
				entity.toNew();
				reportService.save(entity);
			} else {
				for (Map.Entry<Integer, Integer> entry : getMap.entrySet()) {
					Integer getKeyInteger = entry.getKey();
					Integer getValueInteger = entry.getValue();
					FranchiseeReport entity = franchService.getReportEntity(
							getKeyInteger, getCurrentTimeString);
					entity.setType(FranchiseeReportType.date);
					entity.setYear(year);
					entity.setMonth(month);
					entity.setDate(cal.getTime());

					entity.setOrganizationId(departId);
					entity.setOwnerId(getKeyInteger);

					entity.setTotalCount(getValueInteger);
					entity.setUnTrackCount(getValueInteger.intValue()
							- entity.getTrackCount());
					entity.toNew();
					reportService.save(entity);
				}
			}
		}

		// 2.根据员工信息查询对应客户状态的数据，保存FranchiseeReport信息

		// 3.注意：这里需要处理上下级状态，数据展现出来是树结构
		// 4.注意:类，代码，方法的设计，不要堆在一起
		// 5.这里只是一个入口
	}
}
