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
		
		//以上要在基类里扩展出一个方法


		// 的默认查询条件
		String defaultFilter = this.getDefaultFilter();
		if (!StringManager.isNullOrEmpty(defaultFilter)) {

			filters.add(defaultFilter);
		}

		// 扩展条件
		String extraFilter = this.getExtraFilter();
		if (!StringManager.isNullOrEmpty(extraFilter)) {

			filters.add(extraFilter);
		}

		// 表格设置的条件
		String datagridFilter = this.context.getDatagrid().getFilter();
		System.out.println("query datagridFilter:" + datagridFilter);
		if (!StringManager.isNullOrEmpty(filter) && !StringManager.isNullOrEmpty(datagridFilter)) {

			datagridFilter = datagridFilter.replace("1=0", "1=1");
		}

		if (!StringManager.isNullOrEmpty(datagridFilter)) {

			filters.add(datagridFilter);
		}

		filter = StringManager.join(" and ", filters);

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
