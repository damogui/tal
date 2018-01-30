package com.gongsibao.crm.service.action.task.allocation.auto;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.crm.base.INCustomerChangeService;
import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerChange;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.NAllocationType;
import com.gongsibao.entity.crm.dic.NotifyType;

/**
 * @author zhangchao 写入跟进记录
 */
public class ActionAutoAllocationRecordLog implements IAction {
	//任务
	INCustomerTaskService nCustomerTaskService = ServiceFactory.create(INCustomerTaskService.class);
	// 流转日志
	INCustomerChangeService changeService = ServiceFactory.create(INCustomerChangeService.class);
	// 通知日志
	INCustomerTaskNotifyService notifyService = ServiceFactory.create(INCustomerTaskNotifyService.class);

	@Override
	public void execute(ActionContext ctx) {
		NCustomerTask entity = (NCustomerTask) ctx.getItem();
		Integer FormUserId = entity.getOwnerId() == null ? 0 : entity.getOwnerId();
		// 获取最新的任务
		entity = nCustomerTaskService.byId(entity.getId());
		//添加日志
		addRecord(entity, FormUserId);
	}

	//添加日志
	private void addRecord(NCustomerTask entity, Integer FormUserId) {
		//当分配方式是：【手动分配】-提醒售前客服负责人进行手动分配
		if(entity.getAllocationType().equals(NAllocationType.MANUAL)){
			
		}
		
		// 1.保存流转日志
		NCustomerChange changeEntity = new NCustomerChange();
		changeEntity.toNew();// 标示下类型，有多种
		changeEntity.setFormUserId(FormUserId);
		changeEntity.setToUserId(entity.getOwnerId());
		changeEntity.setChangeType(ChangeType.ALLOCATION);
		changeEntity.setTaskId(entity.getId());
		changeEntity.setSupplierId(entity.getSupplierId());
		changeEntity.setDepartmentId(entity.getDepartmentId());
		changeEntity.setCustomerId(entity.getCustomerId());
		changeService.save(changeEntity);
		// 2.保存通知日志
		NCustomerTaskNotify notifyEntity = new NCustomerTaskNotify();
		notifyEntity.toNew();
		notifyEntity.setCustomerId(entity.getCustomerId());
		notifyEntity.setTaskId(entity.getId());
		notifyEntity.setType(NotifyType.SYSTEM);
		notifyEntity.setSupplierId(entity.getSupplierId());
		notifyEntity.setDepartmentId(entity.getDepartmentId());
		notifyService.save(notifyEntity);
	}

}
