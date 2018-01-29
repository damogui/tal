package com.gongsibao.crm.service.action.task.autoAllot;

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

		// 校验状态
		if (entity.getAllocationState() != AllocationState.WAIT) {
			throw new BusinessException("该任务状态不是【待分配】，禁止分配!");
		}

		// 已经有业务员了
		if (!entity.getOwnerId().equals(0)) {
			throw new BusinessException("该任务已经有业务员了，禁止分配!");
		}

		// 分配状态的判断
		if (!entity.getAllocationType().equals(NAllocationType.AUTO) && !entity.getAllocationType().equals(NAllocationType.SemiAutomatic)) {
			throw new BusinessException("该任务分配方式不是【自动分配】、【半自动分配】，禁止自动分配!");
		}

		//当是有市场投放时，则该任务必须要有市场投放的部门
		if (entity.getCosted()&&(entity.getCostSupplierId()==null||entity.getCostSupplierId().equals(0))) {
			throw new BusinessException("当有市场投放时，则该任务必须要有市场投放的部门!");
		}
	}
}
