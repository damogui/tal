package com.gongsibao.crm.web;

import org.netsharp.communication.ServiceFactory;

import com.gongsibao.crm.base.INCustomerTaskService;

public class TaskLostOrderListPart extends TaskAllListPart {

	/**
	 * 任务抽查
	 * @param taskId 任务Id
	 * @param state 任务状态
	 * @param content 记录内容
	 * @param type 抽查类型
	 * @return
	 */
	public boolean abnormal(Integer taskId,Integer state,String content,Integer type){
		INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
		return taskService.abnormal(taskId,state,content,type);
	}
}
