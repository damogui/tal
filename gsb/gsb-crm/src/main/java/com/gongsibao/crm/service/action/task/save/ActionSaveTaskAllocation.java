package com.gongsibao.crm.service.action.task.save;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.AllocationState;
import com.gongsibao.entity.crm.dic.NAllocationType;

/**
 * @author hw 保存任务：新增状态，如果是立即分配，则执行分配
 */
public class ActionSaveTaskAllocation implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTask task = (NCustomerTask) ctx.getItem();

		// 分配状态
		AllocationState allocationState = task.getAllocationState();
		if (allocationState == AllocationState.ALLOCATED || allocationState == AllocationState.NOALLOCATED) {

			//分配状态为【不分配】、【已分配】时不执行分配
			return;
		}

		NAllocationType allocationType = task.getAllocationType();
		if (allocationType == NAllocationType.AUTO) {
			
			// 自动分配，立即分配时调用

		} else if (allocationType == NAllocationType.SemiAutomatic) {

			// 半自动分配，立即分配时调用
		}
	}
}
