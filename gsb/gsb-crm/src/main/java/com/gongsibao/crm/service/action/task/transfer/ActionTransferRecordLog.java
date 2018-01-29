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
			NCustomerChange changeEntity = new NCustomerChange();
			changeEntity.toNew();//标示下类型，有多种
			changeEntity.setFormUserId((Integer)getMap.get("formUserId"+item)); 
			changeEntity.setToUserId((Integer)getMap.get("toUserId"));
			changeEntity.setChangeType(ChangeType.TRANSFER);
			changeEntity.setTaskId(Integer.valueOf(item));
			changeEntity.setSupplierId((Integer)getMap.get("supplierId"));
			changeEntity.setDepartmentId((Integer)getMap.get("departmentId"));  
			changeEntity.setCustomerId((Integer)getMap.get("customerId"+item));
			changeService.save(changeEntity);
			
			//2.保存通知日志
			INCustomerTaskNotifyService notifyService = ServiceFactory.create(INCustomerTaskNotifyService.class);
			NCustomerTaskNotify notifyEntity = new NCustomerTaskNotify();
			notifyEntity.toNew();
			notifyEntity.setCustomerId((Integer)getMap.get("customerId"+item));
			notifyEntity.setTaskId(Integer.valueOf(item));
			notifyEntity.setType(NotifyType.SYSTEM);
			notifyEntity.setSupplierId((Integer)getMap.get("supplierId"));
			notifyEntity.setDepartmentId((Integer)getMap.get("departmentId"));
			notifyService.save(notifyEntity);
		}
	}
}
