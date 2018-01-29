package com.gongsibao.crm.web;

import java.util.ArrayList;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.crm.base.INCustomerTaskService;

public class TaskBaseListPart extends AdvancedListPart {

	public String getFilterByParameter(FilterParameter parameter) {

		if(parameter.getKey().equals("keyword")){

			// 这里全匹配
			ArrayList<String> filters = new ArrayList<String>();
			String keyword = parameter.getValue1().toString();
			filters.add("id='" + keyword + "'");
			filters.add("name='" + keyword + "'");
			filters.add("customer.id='" + keyword + "'");
			filters.add("customer.real_name='" + keyword + "'");
			filters.add("customer.mobile='" + keyword + "'");
			filters.add("customer.telephone='" + keyword + "'");
			filters.add("customer.qq='" + keyword + "'");
			filters.add("customer.weixin='" + keyword + "'");
			return "(" + StringManager.join(" or ", filters) + ")";
			
		}else if(parameter.getKey().equals("unFollowDayCount")){
			
			//未跟进天数：当前时间-上次跟进时间
			return String.format("(datediff(now(),last_follow_time)>%s and (datediff(now(),last_follow_time) )<%s)", parameter.getValue1(),parameter.getValue2());
		}
		return parameter.getFilter();
	}

	
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
	public boolean openMember(String customerIdsStr){
		
		INCustomerService customerService = ServiceFactory.create(INCustomerService.class);
		return customerService.openMember(customerIdsStr);
	}
	/**
	 * 抽查异常处理
	 * @param taskId  任务Id
	 * @param state  1-"未抽查",2-"抽查正常",3-"抽查异常",4-"异常已处理"
	 * @param content 处理内容
	 * @param type  1-"抽查",2-"处理"
	 * @return
	 */
	public boolean abnormal(Integer taskId, Integer state, String content, Integer type){
		INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
		return taskService.abnormal(taskId, state, content, type);
	}
	
}
