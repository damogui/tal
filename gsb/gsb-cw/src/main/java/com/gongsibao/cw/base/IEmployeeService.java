package com.gongsibao.cw.base;

import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.organization.entity.Employee;

public interface IEmployeeService extends IPersistableService<Employee>{

	/**
	 * 获取登录同组织机构下主管
	* @Title: getEmployeeByLeader  
	* @Description: TODO
	* @param @param departmentId
	* @param @return    参数  
	* @return Employee    返回类型  
	* @throws
	 */
	public List<Employee> getEmployeeByLeader(Integer departmentId);
	
	/**
	 * 获取登录人上一级组织机构主管领导
	* @Title: getEmployeeByParentLeader  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param departmentId
	* @param @return    参数  
	* @return List<Employee>    返回类型  
	* @throws
	 */
	public List<Employee> getEmployeeByParentLeader(Integer departmentId);
}
