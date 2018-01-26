package com.gongsibao.crm.web;

import java.util.ArrayList;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.supplier.web.panda.BaseSupplierListPart;

public class TaskBaseListPart extends BaseSupplierListPart{

	@Override
	public String getFilters(){

		ArrayList<String> filters = new ArrayList<String>();
		
		FilterParameter fp = filterMap.get("keyword");
		if(fp != null){

			//这里全匹配
			String keyword = fp.getValue1().toString();
			filters.add("id='"+keyword+"'");
			filters.add("name='"+keyword+"'");
			filters.add("customer.id='"+keyword+"'");
			filters.add("customer.realName='"+keyword+"'");
			filters.add("customer.mobile='"+keyword+"'");
			filters.add("customer.telephone='"+keyword+"'");
			filters.add("customer.qq='"+keyword+"'");
			filters.add("customer.weixin='"+keyword+"'");
		}

		filter = StringManager.join(" or ", filters);
		System.out.println("query filter:" + filter);
		return filter;
	}
	
	/**
	 * 任务分配
	 * @param taskId
	 * @param supplierId
	 * @param departmentId
	 * @param toUserId
	 * @return
	 */
	public boolean allocation(Integer taskId, Integer supplierId, Integer departmentId, Integer toUserId,Integer allocationType){
		INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
		return taskService.allocation(taskId, supplierId, departmentId, toUserId,allocationType);
	}
	/**
	 * 任务退回
	 * @param taskId
	 * @param getNote
	 * @return
	 */
	public boolean rollback(Integer taskId, String getNote){
		INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
		return taskService.rollback(taskId, getNote);
	}
	
}
