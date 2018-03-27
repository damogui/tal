package com.gongsibao.crm.service.action.task.save;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.crm.base.INCustomerOperationLogService;
import com.gongsibao.entity.crm.NCustomerOperationLog;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.AllocationState;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.NAllocationType;
import com.gongsibao.entity.crm.dic.OperationType;

/**
 * @author hw
 * 新增商机：保存日志
 */
public class ActionSaveRecordLog implements IAction{

	INCustomerOperationLogService service = ServiceFactory.create(INCustomerOperationLogService.class);
	
	@Override
	public void execute(ActionContext ctx) {

		NCustomerTask task = (NCustomerTask) ctx.getItem();
		EntityState state = ctx.getState();
		String creator = SessionManager.getUserName();
		String content = null; 
		if(state == EntityState.New){
			
			content = String.format("[%s]创建商机", creator); 
		}else{
			
			content = String.format("[%s]编辑商机", creator); 
		}
		NCustomerOperationLog changeLog = new NCustomerOperationLog();
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
			content = String.format("[%s]分配商机给[%s]", creator,task.getOwner().getName()); 
			changeLog = new NCustomerOperationLog();
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
