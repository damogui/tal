//package com.gongsibao.crm.web;
//
//import java.nio.channels.NotYetBoundException;
//
//import org.netsharp.communication.ServiceFactory;
//import org.netsharp.panda.commerce.ListPart;
//
//import com.gongsibao.crm.base.INCustomerChangeService;
//import com.gongsibao.crm.base.INCustomerService;
//import com.gongsibao.crm.base.INCustomerTaskNotifyService;
//import com.gongsibao.crm.base.INCustomerTaskService;
//import com.gongsibao.entity.crm.NCustomerChange;
//import com.gongsibao.entity.crm.NCustomerTaskNotify;
//import com.gongsibao.entity.crm.dic.ChangeType;
//import com.gongsibao.entity.crm.dic.NotifyType;
///**
// * 我的任务列表操作功能集合
// * @author Administrator
// *
// */
//
//public class MyAllTaskListPart extends ListPart{
//	/**
//	 * 开通会员功能
//	 * @param customerId
//	 * @return
//	 */
//	public int openMember(Integer customerId){
//		INCustomerService customerService = ServiceFactory.create(INCustomerService.class);
//		return customerService.updateIsMember(customerId);
//	}
//	/**
//	 * 任务释放
//	 * @param taskId 任务Id
//	 * @param customerId 客户Id
//	 * @param ownerId 来自那个业务员
//	 * @param content  退回原因
//	 * @return
//	 */
//	public int operatTaskRelease(Integer taskId,Integer customerId,Integer ownerId,String content ){
//		//1.修改业务员Id为空null，此时该任务进入公海
//		INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
//		int getResult = taskService.taskRelease(taskId);
//		//2.添加任务流转
//		INCustomerChangeService changeService = ServiceFactory.create(INCustomerChangeService.class);
//		NCustomerChange changeEntity = new NCustomerChange();
//		changeEntity.toNew();//标示下类型，有多种
//		changeEntity.setFormUserId(ownerId);
//		changeEntity.setContent(content);
//		changeEntity.setChangeType(ChangeType.RELEASE);
//		changeEntity.setTaskId(taskId);
//		changeEntity.setCustomerId(customerId);
//		changeService.save(changeEntity);
//		
//		return getResult;
//	}
//	/**
//	 * 任务转移
//	 * @param taskId 任务Id
//	 * @param customerId 客户Id
//	 * @param supplierId 服务商Id
//	 * @param departmentId 部门Id
//	 * @param formUserId 来自的业务员Id 
//	 * @param toUserId 去向的业务员Id
//	 * @return
//	 */
//	public int operatTaskTransfer(Integer taskId,Integer customerId,Integer supplierId,Integer departmentId,Integer formUserId,Integer toUserId ){
//		//1.修改业务员Id为空null，此时该任务进入公海
//		INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
//		int getResult = taskService.taskTransfer(taskId,supplierId,departmentId,toUserId);
//		//2.添加任务流转
//		INCustomerChangeService changeService = ServiceFactory.create(INCustomerChangeService.class);
//		NCustomerChange changeEntity = new NCustomerChange();
//		changeEntity.toNew();//标示下类型，有多种
//		changeEntity.setFormUserId(formUserId);
//		changeEntity.setToUserId(toUserId);
//		changeEntity.setChangeType(ChangeType.TRANSFER);
//		changeEntity.setTaskId(taskId);
//		changeEntity.setSupplierId(supplierId);
//		changeEntity.setDepartmentId(departmentId);
//		changeEntity.setCustomerId(customerId);
//		changeService.save(changeEntity);
//		//3.添加通知记录
//		INCustomerTaskNotifyService notifyService = ServiceFactory.create(INCustomerTaskNotifyService.class);
//		NCustomerTaskNotify notifyEntity = new NCustomerTaskNotify();
//		notifyEntity.toNew();
//		notifyEntity.setCustomerId(customerId);
//		notifyEntity.setTaskId(taskId);
//		notifyEntity.setType(NotifyType.SYSTEM);
//		notifyEntity.setSupplierId(supplierId);
//		notifyEntity.setDepartmentId(departmentId);
//		notifyService.save(notifyEntity);
//		return getResult;
//	}
//}