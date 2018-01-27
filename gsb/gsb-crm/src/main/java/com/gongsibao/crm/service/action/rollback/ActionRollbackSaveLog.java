package com.gongsibao.crm.service.action.rollback;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.crm.base.INCustomerChangeService;
import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.entity.crm.NCustomerChange;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.NotifyType;

/**
 * @author hw
 * 回退：记录日志
 */
public class ActionRollbackSaveLog  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		NCustomerTask getEntity = (NCustomerTask)ctx.getItem();
		String content = ctx.getStatus().get("content").toString();
		//保存流转日志
		INCustomerChangeService changeService = ServiceFactory.create(INCustomerChangeService.class);
		NCustomerChange changeEntity = new NCustomerChange();
		changeEntity.toNew();//标示下类型，有多种
		changeEntity.setFormUserId(getEntity.getOwnerId()); 
		changeEntity.setContent(content);
		changeEntity.setChangeType(ChangeType.RELEASE);
		changeEntity.setTaskId(getEntity.getId());
		changeEntity.setSupplierId(getEntity.getSupplierId());
		changeEntity.setDepartmentId(getEntity.getDepartmentId());
		changeEntity.setCustomerId(getEntity.getCustomerId());
		changeService.save(changeEntity);
		//2.保存通知日志
		INCustomerTaskNotifyService notifyService = ServiceFactory.create(INCustomerTaskNotifyService.class);
		NCustomerTaskNotify notifyEntity = new NCustomerTaskNotify();
		notifyEntity.toNew();
		notifyEntity.setCustomerId(getEntity.getCustomerId());
		notifyEntity.setTaskId(getEntity.getId());
		notifyEntity.setType(NotifyType.SYSTEM);
		notifyEntity.setSupplierId(getEntity.getSupplierId());
		notifyEntity.setDepartmentId(getEntity.getDepartmentId());
		notifyService.save(notifyEntity);
	}

}
