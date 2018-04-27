package com.gongsibao.account.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.acount.Account;

public interface IAccountService extends IPersistableService<Account> {

	Account getById(Integer id);

	/**   
	 * @Title: hasMobile   
	 * @Description: TODO(手机号是否存在)   
	 * @param: @param mobile
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	Boolean hasMobile(String mobile);
	
	/**   
	 * @Title: byMobile   
	 * @Description: TODO(根据手机号查询)   
	 * @param: @param mobile
	 * @param: @return      
	 * @return: Account      
	 * @throws   
	 */
	Account byMobile(String mobile);

	Integer updateTicket(Integer accountPkid, String ticket);
	/**
	 * @Description:TODO 微信粉丝与账户绑定
	 * @param   mobile openId
	 * @return
	 * @author hbpeng <hbpeng@gongsibao.com>
	 * @date 2018/4/24 14:38
	 */
	Account updateAccount(String mobile, String openId);

	Boolean updateFansId(Integer id, Integer fansId);
	/**
	 * @Description:TODO
	 * @param  mobile openId sceneStr 二维码携带的参数 
	 * @return
	 * @author hbpeng <hbpeng@gongsibao.com>
	 * @date 2018/4/27 14:58 
	 */
	Account updateAccount(String mobile, String openId,String sceneStr);
}