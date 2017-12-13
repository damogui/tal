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
	 * 根据生成报表的类型，生成员工的年、月报
	 * @param getMap  key-departId(部门id)、value-employeeIds(部门下的员工集合)
	 * @param ReportType 统计类型： 1-年、2-月、4-日
	 */
	public void createYearMonthReport(Map<Integer, List<Integer>> departmentMap,FranchiseeReportType reportType);
	/**
	 * 生成部门的年、月、日报表
	 * @param departmentId 部门id
	 * @param ReportType 统计类型： 1-年、2-月、4-日
	 */
	public void createDepartReport(Integer departmentId,FranchiseeReportType reportType);
}
