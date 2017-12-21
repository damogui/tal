package com.gongsibao.uc.base;

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
	
}