package com.gongsibao.uc.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.uc.Organization;

public interface IOrganizationService extends IPersistableService<Organization> {
	

	/**   
	 * @Title: hasChildDepartment
	 * @Description: TODO(判断是否有子部门)   
	 * @param: @param departmentId
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	Boolean hasChildDepartment(Integer departmentId);
	
	/**   
	 * @Title: getChildDepartmentIdList   
	 * @Description: TODO(获取子部门Id)   
	 * @param: @param departmentId
	 * @param: @return      
	 * @return: List<Integer>      
	 * @throws   
	 */
	List<Integer> getChildDepartmentIdList(Integer departmentId);
}