package com.gongsibao.crm.web;

import org.netsharp.communication.ServiceFactory;

import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.crm.base.INCustomerTaskService;


public class TaskAllListPart extends TaskBaseListPart{

	INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
	
	/**
	 * 任务分配
	 * @param taskId
	 * @param supplierId
	 * @param departmentId
	 * @param toUserId
	 * @return
	 */
	public boolean allocation(String taskId, Integer supplierId, Integer departmentId, Integer toUserId,Integer allocationType){
		
		return taskService.allocation(taskId, supplierId, departmentId, toUserId,allocationType);
	}
	/**
	 * 任务收回
	 * @param taskId
	 * @param getNote
	 * @return
	 */
	public boolean regain(String taskIds, String getNote){
		
		return taskService.regain(taskIds, getNote);
	}
	/**
	 * 任务退回
	 * @param taskId
	 * @param getNote
	 * @return
	 */
	public boolean rollback(Integer taskId, String getNote){

		return taskService.rollback(taskId, getNote);
	}
	/**
	 * 任务转移
	 * @param taskId
	 * @param supplierId
	 * @param departmentId
	 * @param toUserId
	 * @return
	 */
	public boolean transfer(String taskId, Integer supplierId, Integer departmentId, Integer toUserId){

		return taskService.transfer(taskId, supplierId, departmentId, toUserId);
	}
	/**
	 * 开通会员功能
	 * @param customerId
	 * @return
	 */
	public boolean openMember(Integer customerId){
		
		INCustomerService customerService = ServiceFactory.create(INCustomerService.class);
		return customerService.updateIsMember(customerId);
	}
}
