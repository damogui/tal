package com.gongsibao.crm.service.action.task.allocation.auto;

import java.util.Date;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;

import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.AllocationState;
import com.gongsibao.entity.crm.dic.CustomerFollowStatus;

/**
 * @author zhangchao
 *任务的分配状态的回写
 */
public class ActionAutoAllocationUpdateTaskStatus implements IAction {

	INCustomerTaskService nCustomerTaskService = ServiceFactory.create(INCustomerTaskService.class);
	
	@Override
	public void execute(ActionContext ctx) {
		NCustomerTask entity = (NCustomerTask) ctx.getItem();

		// 验证非空
		if (entity == null) {
			throw new BusinessException("该任务不存在！");
		}
		
		entity = nCustomerTaskService.byId(entity.getId());
		//状态改为【已分配】
		entity.setAllocationState(AllocationState.ALLOCATED);
		//跟进状态改为【待跟进】
		entity.setFoolowStatus(CustomerFollowStatus.UNFollow);
		//跟新最后分配时间
		entity.setLastAllocationTime(new Date());
		
		nCustomerTaskService.save(entity);
		
	}
}
