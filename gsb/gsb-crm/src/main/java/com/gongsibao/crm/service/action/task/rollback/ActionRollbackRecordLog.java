package com.gongsibao.crm.service.action.task.rollback;

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
public class ActionRollbackRecordLog  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		
		NCustomerTask task = (NCustomerTask)ctx.getItem();
		String content = ctx.getStatus().get("content").toString();
		
		//保存流转日志
		INCustomerChangeService changeService = ServiceFactory.create(INCustomerChangeService.class);
		NCustomerChange changeLog = new NCustomerChange();{

			changeLog.toNew();//标示下类型，有多种
			changeLog.setFormUserId(task.getOwnerId()); 
			changeLog.setContent(content);
			changeLog.setChangeType(ChangeType.RELEASE);
			changeLog.setTaskId(task.getId());
			changeLog.setSupplierId(task.getSupplierId());
			changeLog.setDepartmentId(task.getDepartmentId());
			changeLog.setCustomerId(task.getCustomerId());
		}
		changeService.save(changeLog);
		
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
