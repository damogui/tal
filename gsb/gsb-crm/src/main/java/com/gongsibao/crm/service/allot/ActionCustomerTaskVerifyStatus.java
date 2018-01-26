package com.gongsibao.crm.service.allot;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.BusinessException;

import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.AllocationState;

/**
 * @author zhangchao 状态验证
 */
public class ActionCustomerTaskVerifyStatus implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTask entity = (NCustomerTask) ctx.getItem();
		
		//验证非空
		if(entity==null){
			throw new BusinessException("该任务不存在！");
		}
		
		// 1.校验状态
		if (entity.getAllocationState() != AllocationState.WAIT) {
			throw new BusinessException("该任务状态不是【待分配】，禁止分配!");
		}
	}
}
