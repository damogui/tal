package com.gongsibao.crm.service.action.task.rollback;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.crm.base.INCustomerChangeService;
import com.gongsibao.entity.crm.NCustomerChange;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.ChangeType;

/**
 * @author hw
 * 回退：记录日志
 */
public class ActionRollbackRecordLog  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		
		NCustomerTask getEntity = (NCustomerTask)ctx.getItem();
		String content = ctx.getStatus().get("content").toString();
		
		//保存流转日志
		INCustomerChangeService changeService = ServiceFactory.create(INCustomerChangeService.class);
		NCustomerChange changeLog = new NCustomerChange();{

			changeLog.toNew();//标示下类型，有多种
			changeLog.setFormUserId(getEntity.getOwnerId()); 
			changeLog.setContent(content);
			changeLog.setChangeType(ChangeType.RELEASE);
			changeLog.setTaskId(getEntity.getId());
			changeLog.setSupplierId(getEntity.getSupplierId());
			changeLog.setDepartmentId(getEntity.getDepartmentId());
			changeLog.setCustomerId(getEntity.getCustomerId());
		}
		changeService.save(changeLog);
	}
}
