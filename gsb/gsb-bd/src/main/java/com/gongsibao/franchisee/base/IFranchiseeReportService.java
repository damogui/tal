package com.gongsibao.franchisee.base;

import java.util.List;
import java.util.Map;

import org.netsharp.base.IPersistableService;
import org.netsharp.organization.entity.Organization;

import com.gongsibao.entity.franchisee.FranchiseeReport;
import com.gongsibao.entity.franchisee.dic.FranchiseeReportType;

public interface IFranchiseeReportService  extends IPersistableService<FranchiseeReport>{

	/**
	 * 生成员工日报
	 * @param employeeId 员工id
	 * @param employeeName 员工name
	 * @param parentId 该员工父Id
	 * @param orgaId 组织机构Id
	 */
	public void createStaffDayReport(Integer employeeId,String employeeName,Integer parentId,Integer orgaId);
	/**
	 * 根据报表的类型，生成员工的年、月报
	 * @param employeeId 员工id
	 * @param employeeName 员工name
	 * @param parentId 父Id
	 * @param orgaId 组织机构Id
	 * @param reportType 报表类型
	 */
	public void createStaffYearMonthReport(Integer employeeId,String employeeName, Integer parentId,Integer orgaId,FranchiseeReportType reportType);
	
	/**
	 * 根据orgaId(组织机构表中的Id)获取‘组织机构表’集合
	 * @param orgaId 组织机构表的主键
	 * @return
	 */
	public List<Organization> getOranListByOrgaId(Integer orgaId);
	/**
	 * 根据organizationId（组织机构表中的Id） 递归‘组织机构’表获取组织结构
	 * @param orgaId 组织机构表的主键
	 * @return
	 */
	public Map<Integer, Integer> recursiveByOrgaId(Map<Integer, Integer> map,Integer orgaId);
	/**
	 * 根据供应商报表中的orgaId，获取供应商报表的主键。
	 * @param orgaId 供应商报表中的organization_id
	 * @param ReportType 统计类型： 1-年、2-月、4-日
	 * @return
	 */
	public Integer getReportIdByOrganId(Integer orgaId,FranchiseeReportType reportType);
	/**
	 * 根据orgaId（组织机构表中的Id），获取员工id
	 * @param orgaId 组织机构表的主键
	 * @return Map<Integer, String> id  name
	 */
	public Map<Integer, String> getEmployeeIdByOrganId(Integer orgaId);
	
	/**
	 * 生成员工父节点的报表
	 * @param parentId 父Id
	 * @param reportEntity 报表实体
	 * @param ReportType 统计类型： 1-年、2-月、4-日
	 */
	public void createDirectoryReport(Integer parentId,FranchiseeReport reportEntity,FranchiseeReportType reportType);
	/**
	 * 统计员工上级的数量
	 * @param ReportType 统计类型： 1-年、2-月、4-日
	 */
	public void statDireReportNo(FranchiseeReportType reportType);
	
}
