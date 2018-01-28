package com.gongsibao.crm.service.action.task.save;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.crm.base.INCustomerChangeService;
import com.gongsibao.entity.crm.NCustomerChange;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.AllocationState;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.NAllocationType;
import com.gongsibao.entity.crm.dic.OperationType;

/**
 * @author hw
 * 新增任务：保存日志
 */
public class ActionSaveTaskLog implements IAction{

	INCustomerChangeService service = ServiceFactory.create(INCustomerChangeService.class);
	
	@Override
	public void execute(ActionContext ctx) {

		NCustomerTask task = (NCustomerTask) ctx.getItem();
		EntityState state = ctx.getState();
		String creator = SessionManager.getUserName();
		String content = null; 
		if(state == EntityState.New){
			
			content = String.format("[%s]创建任务", creator); 
		}else{
			
			content = String.format("[%s]编辑任务", creator); 
		}
		NCustomerChange changeLog = new NCustomerChange();
		{
			changeLog.toNew();
			changeLog.setChangeType(ChangeType.INPUT);
			changeLog.setType(OperationType.MANUAL);
			changeLog.setCustomerId(task.getCustomerId());
			changeLog.setFormUserId(SessionManager.getUserId());
			changeLog.setTaskId(task.getId());
			changeLog.setContent(content);
			changeLog.setSupplierId(task.getSupplierId());
			changeLog.setDepartmentId(task.getDepartmentId());
			service.save(changeLog);
		}
		
		
		//记录分配日志
		NAllocationType allocationType = task.getAllocationType();
		AllocationState allocationState = task.getAllocationState();
		
		// 分配方式为【手动分配】、分配状态为【待分配】
		if (allocationType == NAllocationType.MANUAL && allocationState == AllocationState.WAIT) {
		
			//task.getOwner()可能为空
			content = String.format("[%s]分配任务给[%s]", creator,task.getOwner().getName()); 
			changeLog = new NCustomerChange();
			{
				changeLog.toNew();
				changeLog.setChangeType(ChangeType.INPUT);
				changeLog.setType(OperationType.MANUAL);
				changeLog.setCustomerId(task.getCustomerId());
				changeLog.setFormUserId(SessionManager.getUserId());
				changeLog.setToUserId(task.getOwnerId());
				changeLog.setTaskId(task.getId());
				changeLog.setContent(content);
				changeLog.setSupplierId(task.getSupplierId());
				changeLog.setDepartmentId(task.getDepartmentId());
			}
		}
	}
}
