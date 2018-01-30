package com.gongsibao.crm.service.action.task.rollback;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.NotifyType;

/**
 * @author hw
 * 回退:发送消息
 */
public class ActionRollbackSendMessage implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		
		NCustomerTask task = (NCustomerTask)ctx.getItem();
		
		//2.保存通知日志
		INCustomerTaskNotifyService notifyService = ServiceFactory.create(INCustomerTaskNotifyService.class);
		NCustomerTaskNotify notify = new NCustomerTaskNotify();{

			notify.toNew();
			notify.setCustomerId(task.getCustomerId());
			notify.setTaskId(task.getId());
			notify.setType(NotifyType.SYSTEM);
			notify.setSupplierId(task.getSupplierId());
			notify.setDepartmentId(task.getDepartmentId());
		}
		notifyService.save(notify);
	}

}
