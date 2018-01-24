package com.gongsibao.crm.web;

import org.apache.commons.lang.ObjectUtils.Null;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.crm.base.INCustomerChangeService;
import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerChange;
import com.gongsibao.entity.crm.dic.ChangeType;
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
	 * @param customerId 客户Id
	 * @param ownerId 来自那个业务员
	 * @param content  退回原因
	 * @return
	 */
	public int operatBackTask(Integer taskId,Integer customerId,Integer ownerId,String content ){
		//1.修改业务员Id为空null，此时该任务进入公海
		INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
		int getResult = taskService.insertHighSeas(taskId);
		//2.添加任务流转
		INCustomerChangeService changeService = ServiceFactory.create(INCustomerChangeService.class);
		NCustomerChange changeEntity = new NCustomerChange();
		changeEntity.toNew();//标示下类型，有多种
		changeEntity.setFormUserId(ownerId);
		changeEntity.setContent(content);
		changeEntity.setChangeType(ChangeType.RELEASE);
		changeEntity.setTaskId(taskId);
		changeEntity.setCustomerId(customerId);
		changeService.save(changeEntity);
		
		return getResult;
	}
}