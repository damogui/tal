package org.netsharp.organization.base;

import java.util.List;
import java.util.Map;

import org.netsharp.base.IPersistableService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Organization;

public interface IOrganizationService extends IPersistableService<Organization> {
	/* 得到所有的岗位，好像是在员工注册时候使用 */
	Map<Integer, String> getPosts();

	/* 根据部门职能类型查询组织节点，比如查询所有的门店 */
	List<Organization> getByFunction(String organizationFunctionType);

	/* 根据员工查找所属的部门 */
	List<Organization> getDirectDepartmentByEmployeeId(Integer employeeId);

	/**
	 * 按照部门id获取直接领导
	 */
	Employee getDirectLeaderByDepartmentId(Integer departmentId);

	/**
	 * 按照岗位字符串获取唯一雇员,用于工作流,position对应PositionType枚举
	 */
	Employee getFitableEmployeeByPositonAndOrgNodeName(String position, String departmentName);

	/**
	 * 按照部门的pathCode获取所直属公司
	 */
	Organization getCorprationByDepartment(String pathCode);

	/* 找到当前部门的所有岗位的所有员工，不递归查询 */
	List<Employee> getEmployeesByCurrentDepartment(Integer departmentId, boolean disabled);

	/**
	 * <p>
	 * 方法名称：getMainDepartment
	 * </p>
	 * <p>
	 * 方法描述：根据员工id获取员工所在部门，取主岗所在部门
	 * </p>
	 * 
	 * @param employeeId
	 * @return
	 * @author gaomeng
	 * @since 2016年1月20日
	 *        <p>
	 *        history 2016年1月20日 gaomeng 创建
	 *        <p>
	 */
	Organization getMainDepartment(Integer employeeId);

	/**
	 * <p>
	 * 方法名称：getParentDepartment
	 * </p>
	 * <p>
	 * 方法描述：根据部门id查询直接上级部门
	 * </p>
	 * 
	 * @param departmentId
	 * @return
	 * @author gaomeng
	 * @since 2016年1月23日
	 *        <p>
	 *        history 2016年1月23日 gaomeng 创建
	 *        <p>
	 */
	Organization getParentDepartment(Organization department);

	/**
	 * 获取部门下的所有子部门
	 * 
	 * @param id
	 * @return
	 */
	List<Integer> getChildDepartment(Integer id);

	void changeParent(Integer nodeId, Integer newParentId);
	
	/**
	 * 停用
	 * @param id
	 * @return
	 */
	Boolean disabled(Integer id);

}
