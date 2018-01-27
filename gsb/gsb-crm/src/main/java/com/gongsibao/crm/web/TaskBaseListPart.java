package com.gongsibao.crm.web;

import java.util.ArrayList;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.supplier.web.panda.BaseSupplierListPart;

public class TaskBaseListPart extends BaseSupplierListPart{

	@Override
	public String getFilters(){

		ArrayList<String> filters = new ArrayList<String>();
		
		if (filterMap.size() > 0) {

			for (String key : filterMap.keySet()) {

				FilterParameter fp = filterMap.get(key);
				if(key == "keyword"){
					
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
				}else{

					String fString = fp.getFilter();
					filters.add(fString);
				}
			}
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
	public boolean allocation(String taskId, Integer supplierId, Integer departmentId, Integer toUserId,Integer allocationType){
		INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
		return taskService.allocation(taskId, supplierId, departmentId, toUserId,allocationType);
	}
	/**
	 * 任务收回
	 * @param taskId
	 * @param getNote
	 * @return
	 */
	public boolean regain(String taskIds, String getNote){
		INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
		return taskService.regain(taskIds, getNote);
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
	/**
	 * 任务转移
	 * @param taskId
	 * @param supplierId
	 * @param departmentId
	 * @param toUserId
	 * @return
	 */
	public boolean transfer(String taskId, Integer supplierId, Integer departmentId, Integer toUserId,Integer allocationType){
		INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
		return taskService.transfer(taskId, supplierId, departmentId, toUserId);
	}
	/**
	 * 开通会员功能
	 * @param customerId
	 * @return
	 */
	public void openMember(Integer customerId){
		INCustomerService customerService = ServiceFactory.create(INCustomerService.class);
		customerService.updateIsMember(customerId);
	}
}
