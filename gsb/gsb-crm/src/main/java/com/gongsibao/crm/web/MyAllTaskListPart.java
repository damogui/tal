package com.gongsibao.crm.web;

import org.apache.commons.lang.ObjectUtils.Null;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.crm.base.INCustomerChangeService;
import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerChange;
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
	/**
	 * 退回任务
	 * @param taskId 任务Id
	 * @param formUserId 来自那个业务员
	 * @param content  退回原因
	 * @return
	 */
	public int operatBackTask(Integer taskId,Integer formUserId,String content ){
		//1.修改最后跟进人为null
		INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
		int getResult = taskService.updateLastFoolowUser(taskId);
		//2.添加任务流转
		INCustomerChangeService changeService = ServiceFactory.create(INCustomerChangeService.class);
		NCustomerChange changeEntity = new NCustomerChange();
		changeEntity.setFormUserId(formUserId);
		changeEntity.setContent(content);
		changeEntity.setTaskId(taskId);
		changeService.save(changeEntity);
		
		return getResult;
	}
}