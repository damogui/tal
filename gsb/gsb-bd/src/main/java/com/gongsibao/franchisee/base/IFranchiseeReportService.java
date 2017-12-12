package com.gongsibao.franchisee.base;

import java.util.List;
import java.util.Map;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.franchisee.FranchiseeReport;
import com.gongsibao.entity.franchisee.dic.FranchiseeReportType;

public interface IFranchiseeReportService  extends IPersistableService<FranchiseeReport>{


	/**
	 * 生成员工日报
	 * @param getMap  key-departId(部门id)、value-employeeIds(部门下的员工集合)
	 */
	public void createDayReport(Map<Integer, List<Integer>> departmentMap);
	/**
	 * 生成员工月报
	 * @param getMap  key-departId(部门id)、value-employeeIds(部门下的员工集合)
	 */
	public void createMonthReport(Map<Integer, List<Integer>> departmentMap);
	/**
	 * 生成部门的报表
	 * @param departmentId 部门id
	 * @param ReportType 统计类型： 1-年、2-月、4-日
	 */
	public void createDepartDayReport(Integer departmentId,FranchiseeReportType reportType);
}
