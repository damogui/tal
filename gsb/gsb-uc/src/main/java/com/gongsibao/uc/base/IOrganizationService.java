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
	
	
	/**   
	 * @Title: getParentIdpartementId   
	 * @Description: TODO(获取上级部门Id)   
	 * @param: @param departmentId
	 * @param: @return      
	 * @return: Integer      
	 * @throws   
	 */
	Integer getParentDepartementId(Integer departmentId);
	
	/**   
	 * @Title: getLateralDepartementIdList   
	 * @Description: TODO(获取同级部门Id集合)   
	 * @param: @param departmentId
	 * @param: @return      
	 * @return: List<Integer>      
	 * @throws   
	 */
	List<Integer> getLateralDepartementIdList(Integer departmentId);
	
	/**   
	 * @Title: getLeafIdList   
	 * @Description: TODO(获取所有子部门集合，递归)   
	 * @param: @param departmentId
	 * @param: @return      
	 * @return: List<Integer>      
	 * @throws   
	 */
	List<Organization> getChildList(Integer departmentId);
	
	
	/**   
	 * @Title: getLeafIdList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param departmentId
	 * @param: @return      
	 * @return: List<Integer>      
	 * @throws   
	 */
	List<Integer> getLeafIdList(Integer departmentId);
}