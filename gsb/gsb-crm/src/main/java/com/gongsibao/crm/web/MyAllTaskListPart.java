package com.gongsibao.crm.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.crm.base.INCustomerService;
/**
 * 我的任务列表操作功能集合
 * @author Administrator
 *
 */

public class MyAllTaskListPart extends ListPart{
	/**
	 * 开通会员功能
	 * @param customerId
	 * @return
	 */
	public int openMember(Integer customerId){
		INCustomerService customerService = ServiceFactory.create(INCustomerService.class);
		return customerService.updateIsMember(customerId);
	}
}