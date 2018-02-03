package com.gongsibao.crm.service.action.task.regain;

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
 * @author hw 收回：记录日志
 */
public class ActionRegainRecordLog implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTask task = (NCustomerTask) ctx.getItem();
		
		// 1.保存流转日志
		String content = ctx.getStatus().get("content").toString();
		INCustomerChangeService changeService = ServiceFactory.create(INCustomerChangeService.class);
		NCustomerChange changeLog = new NCustomerChange();
		{
			changeLog.toNew();
			changeLog.setFormUserId(task.getOwnerId());
			changeLog.setContent(content);
			changeLog.setChangeType(ChangeType.RECYCLE);
			changeLog.setTaskId(task.getId());
			changeLog.setSupplierId(task.getSupplierId());
			changeLog.setDepartmentId(task.getDepartmentId());
			changeLog.setCustomerId(task.getCustomerId());
			changeService.save(changeLog);
		}

		// 2.保存通知日志
		INCustomerTaskNotifyService notifyService = ServiceFactory.create(INCustomerTaskNotifyService.class);
		NCustomerTaskNotify notify = new NCustomerTaskNotify();
		{
			content = String.format("任务ID：%s,被收回，请悉知。", task.getId());
			notify.toNew();
			notify.setTaskId(task.getId());
			notify.setContent(content);
			notify.setType(NotifyType.WEIXIN);
			notify.setCustomerId(task.getCustomerId());
			notify.setSupplierId(task.getSupplierId());
			notify.setDepartmentId(task.getDepartmentId());
			notify.setReceivedId(task.getOwnerId() == null ? 0 : task.getOwnerId());
			notifyService.save(notify);
		}
	}
}
