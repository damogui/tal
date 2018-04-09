package com.gongsibao.crm.web;

import java.util.ArrayList;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import com.gongsibao.crm.base.INCustomerOperationLogService;
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
			filters.add("id='" + keyword + "'");
			filters.add("real_name='" + keyword + "'");
			filters.add("mobile='" + keyword + "'");
			filters.add("telephone='" + keyword + "'");
			filters.add("qq='" + keyword + "'");
			filters.add("weixin='" + keyword + "'");
			return "customer_id in ( select id from  crm_customer where " + StringManager.join(" or ", filters) + ")";
		}else if(parameter.getKey().equals("unFollowDayCount")){
			
			//未跟进天数：当前时间-上次跟进时间
			return String.format("(datediff(now(),last_follow_time)>%s and (datediff(now(),last_follow_time) )<%s)", parameter.getValue1(),parameter.getValue2());
		}
		return parameter.getFilter();
	}

	
	INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
	
	/**
	 * 商机分配
	 * @param taskId
	 * @param supplierId
	 * @param departmentId
	 * @param toUserId
	 * @return
	 */
	public boolean allocation(String taskIds, Integer supplierId, Integer departmentId, Integer toUserId){
		
		String[] taskIdArray = taskIds.split("_");
		return taskService.batchAllocation(taskIdArray, supplierId, departmentId, toUserId);
	}
	/**
	 * 商机收回
	 * @param taskId
	 * @param getNote
	 * @return
	 */
	public boolean regain(String taskIds, String getNote){
		
		String[] taskIdArray = taskIds.split("_");
		return taskService.batchRegain(taskIdArray, getNote);
	}
	/**
	 * 商机退回
	 * @param taskId
	 * @param getNote
	 * @return
	 */
	public boolean rollback(Integer taskId, String getNote){

		return taskService.rollback(taskId, getNote);
	}
	/**
	 * 商机转移
	 * @param taskId
	 * @param supplierId
	 * @param departmentId
	 * @param toUserId
	 * @return
	 */
	public boolean transfer(String taskIds, Integer supplierId, Integer departmentId, Integer toUserId){

		String[] taskIdArray = taskIds.split("_");
		return taskService.batchTransfer(taskIdArray, supplierId, departmentId, toUserId);
	}
	/**
	 * 开通会员功能
	 * @param customerId
	 * @return
	 */
	public boolean openMember(Integer customerId){
		
		INCustomerService customerService = ServiceFactory.create(INCustomerService.class);
		return customerService.openMember(customerId);
	}
	/**
	 * 抽查异常处理
	 * @param taskId  商机Id
	 * @param state  1-"未抽查",2-"抽查正常",3-"抽查异常",4-"异常已处理"
	 * @param content 处理内容
	 * @param type  1-"抽查",2-"处理"
	 * @return
	 */
	public boolean abnormal(Integer taskId, Integer state, String content, Integer type){
		INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
		return taskService.abnormal(taskId, state, content, type);
	}
	
	/**
	 * @Title: recordLookLog
	 * @Description: TODO(记录查看联系方式日志)
	 * @param: @param customerId
	 * @param: @param typeName
	 * @param: @return
	 * @return: boolean
	 * @throws
	 */
	public boolean recordLookLog(Integer customerId, String typeName) {

		INCustomerOperationLogService changeService = ServiceFactory.create(INCustomerOperationLogService.class);
		return changeService.recordLookLog(customerId, typeName);
	}
}
