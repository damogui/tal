package com.gongsibao.crm.service.action.autoAllot;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.BusinessException;

import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.AllocationState;
import com.gongsibao.entity.crm.dic.NAllocationType;

/**
 * @author zhangchao 状态验证
 */
public class ActionCustomerTaskVerifyStatus implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTask entity = (NCustomerTask) ctx.getItem();

		// 验证非空
		if (entity == null) {
			throw new BusinessException("该任务不存在！");
		}

		// 1.校验状态
		if (entity.getAllocationState() != AllocationState.WAIT) {
			throw new BusinessException("该任务状态不是【待分配】，禁止分配!");
		}

		//分配状态的判断
		if (!entity.getAllocationType().equals(NAllocationType.AUTO) && !entity.getAllocationType().equals(NAllocationType.SemiAutomatic)) {
			throw new BusinessException("该任务分配方式不是【自动分配】、【半自动分配】，禁止自动分配!");
		}
	}
}
