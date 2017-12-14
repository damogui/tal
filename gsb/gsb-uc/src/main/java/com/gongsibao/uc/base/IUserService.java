package com.gongsibao.uc.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.uc.User;

public interface IUserService extends IPersistableService<User> {
	
	/**   
	 * @Title: byMobilePhone   
	 * @Description: TODO(根据手机号查询)   
	 * @param: @param mobilePhone
	 * @param: @return      
	 * @return: User      
	 * @throws
	 */
	User byMobilePhone(String mobilePhone);
	
	/**   
	 * @Title: hasMobile   
	 * @Description: TODO(判断是否存在手机号)   
	 * @param: @param id
	 * @param: @param mobile
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	Boolean hasMobile(Integer id,String mobile);
	
	/**   
	 * @Title: getIds   
	 * @Description: TODO(根据部门Id获取下面所有UserId，需要递归处理子部门)   
	 * @param: @param departmentId
	 * @param: @return      
	 * @return: List<Integer>      
	 * @throws   
	 */
	List<Integer> getIdList(Integer departmentId);
}