package com.gongsibao.crm.web;

import java.math.BigDecimal;

import org.netsharp.communication.ServiceFactory;

import com.gongsibao.crm.base.INCustomerProductService;
import com.gongsibao.crm.base.INCustomerTaskFoolowService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.crm.service.action.task.follow.ActionFollowVerify;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;

public class TaskFollowCtrl {

	INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
	
	/**
	 * 商机跟进
	 * @param entity 
	 * @param originalQualityId 没更新前质量Id（用于判断是否改变，发送通知用）
	 * @return
	 */
	public boolean follow(NCustomerTaskFoolow entity,Integer originalQualityId){
		
		INCustomerTaskFoolowService foolowService = ServiceFactory.create(INCustomerTaskFoolowService.class);
		NCustomerTaskFoolow newTaskFoolow = foolowService.newInstance();
		newTaskFoolow.setTaskId(entity.getTaskId());
		newTaskFoolow.setQualityId(entity.getQualityId());
		newTaskFoolow.setNextFoolowTime(entity.getNextFoolowTime());
		newTaskFoolow.setSigningAmount(entity.getSigningAmount());
		newTaskFoolow.setReturnedAmount(entity.getReturnedAmount());
		newTaskFoolow.setContent(entity.getContent());
		return taskService.follow(newTaskFoolow, originalQualityId);
	}
	
	/**
	 * 商机跟进，验证意向产品
	 * @param taskId
	 * @return
	 */
	public boolean hasProduct(Integer taskId){
		ActionFollowVerify verify = new ActionFollowVerify();
		Boolean isHas = verify.hasProduct(taskId);
		return isHas;
	}
	
	
	/**   
	 * @Title: hasDistrict   
	 * @Description: TODO(是否添加意向地区)   
	 * @param: @param taskId
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	public boolean hasDistrict(Integer taskId){
		ActionFollowVerify verify = new ActionFollowVerify();
		Boolean isHas = verify.hasDistrict(taskId);
		return isHas;
	}
	
	/**   
	 * @Title: getSigningAmounjt   
	 * @Description: TODO(根据商机计算估计签单金额)
	 * @param: @param taskId
	 * @param: @return      
	 * @return: Integer      
	 * @throws   
	 */
	public BigDecimal getSigningAmount(Integer taskId){
		
		INCustomerProductService customerProductService = ServiceFactory.create(INCustomerProductService.class);
		return customerProductService.getSigningAmount(taskId);
	}
}
