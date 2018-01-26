package com.gongsibao.crm.service.action.allocation;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.crm.base.INCustomerChangeService;
import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.entity.crm.NCustomerChange;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.NotifyType;

/**
 * @author hw
 * 分配：保存日志
 */
public class ActionAllocationSaveLog implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		NCustomerTask getEntity = (NCustomerTask)ctx.getItem();
		//1.保存流转日志
		INCustomerChangeService changeService = ServiceFactory.create(INCustomerChangeService.class);
		NCustomerChange changeEntity = new NCustomerChange();
		changeEntity.toNew();//标示下类型，有多种
		//changeEntity.setFormUserId(); 来自哪个
		changeEntity.setToUserId(getEntity.getOwnerId());
		changeEntity.setChangeType(ChangeType.ALLOCATION);
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
