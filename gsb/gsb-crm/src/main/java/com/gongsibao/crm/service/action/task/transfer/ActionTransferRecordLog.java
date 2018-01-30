package com.gongsibao.crm.service.action.task.transfer;

import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.crm.base.INCustomerChangeService;
import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.entity.crm.NCustomerChange;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.NotifyType;

/**
 * @author hw
 * 转移：记录日志
 */
public class ActionTransferRecordLog  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		
		Map<String,Object> getMap = ctx.getStatus();
		String [] taskIds = getMap.get("taskIds").toString().split("_");
		for (String item : taskIds) {
			
			//1.保存流转日志
			INCustomerChangeService changeService = ServiceFactory.create(INCustomerChangeService.class);
			NCustomerChange changeLog = new NCustomerChange();{

				changeLog.toNew();//标示下类型，有多种
				changeLog.setFormUserId((Integer)getMap.get("formUserId"+item)); 
				changeLog.setToUserId((Integer)getMap.get("toUserId"));
				changeLog.setChangeType(ChangeType.TRANSFER);
				changeLog.setTaskId(Integer.valueOf(item));
				changeLog.setSupplierId((Integer)getMap.get("supplierId"));
				changeLog.setDepartmentId((Integer)getMap.get("departmentId"));  
				changeLog.setCustomerId((Integer)getMap.get("customerId"+item));
				changeService.save(changeLog);
			}
			
			//2.保存通知日志
			INCustomerTaskNotifyService notifyService = ServiceFactory.create(INCustomerTaskNotifyService.class);
			NCustomerTaskNotify notify = new NCustomerTaskNotify();{

//				String content = String.format("任务ID：%s,被业务员回退，请悉知。", task.getId());
//				notify.toNew();
//				notify.setTaskId(task.getId());
//				notify.setContent(content);
//				notify.setType(NotifyType.WEIXIN);
//				notify.setCustomerId(task.getCustomerId());
//				notify.setSupplierId(task.getSupplierId());
//				notify.setDepartmentId(task.getDepartmentId());
//				notify.setReceivedId(task.getOwnerId());
//				notifyService.save(notify);
				
				notify.setCustomerId((Integer)getMap.get("customerId"+item));
				notify.setTaskId(Integer.valueOf(item));
				notify.setType(NotifyType.SYSTEM);
				notify.setSupplierId((Integer)getMap.get("supplierId"));
				notify.setDepartmentId((Integer)getMap.get("departmentId"));
				notifyService.save(notify);
			}
			
			
		}
	}
}
