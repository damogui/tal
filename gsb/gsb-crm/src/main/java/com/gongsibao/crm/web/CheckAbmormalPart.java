package com.gongsibao.crm.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.crm.base.INCustomerTaskService;

public class CheckAbmormalPart extends ListPart{

	//修改审核状态
	public int taskInspectionState(Integer taskId,Integer selectValue,String getNote){
		INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
		return taskService.updateInspectionState(taskId, selectValue, getNote);
	}
}
