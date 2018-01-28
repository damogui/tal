package com.gongsibao.crm.service.action.task.save;

import java.util.Date;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.base.IPersistableService;
import org.netsharp.core.EntityState;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.ReflectManager;

import com.gongsibao.crm.service.NCustomerService;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.AllocationState;
import com.gongsibao.entity.crm.dic.NAllocationType;

/**
 * @author hw 新增任务：保存
 */
public class ActionSaveTaskPersist implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTask task = (NCustomerTask) ctx.getItem();
		EntityState state = task.getEntityState();
		NAllocationType allocationType = task.getAllocationType();
		if (allocationType == NAllocationType.MANUAL) {

			// 【手动分配】 时设置分配状态为【已经分配】
			task.setAllocationState(AllocationState.ALLOCATED);
			
			if(state == EntityState.New){
				
				//新增状态：设置最后分配时间，最后分配人
				task.setLastAllocationTime(new Date());
				task.setLastAllocationUserId(SessionManager.getUserId());
			}
		}

		@SuppressWarnings("unchecked")
		IPersistableService<NCustomerTask> service = (IPersistableService<NCustomerTask>) ReflectManager.newInstance(NCustomerService.class.getSuperclass());
		task = service.save(task);
		ctx.setItem(task);
	}
}
