package com.gongsibao.crm.service.action.task.remind;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.BusinessException;

import com.gongsibao.entity.crm.NCustomerTask;

/**
 * 提醒：验证
 * @author Administrator
 *
 */
public class ActionRemindVerify implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		NCustomerTask taskEntity = (NCustomerTask) ctx.getItem();
		//校验是否已分配
        if (taskEntity != null) {
        	if(taskEntity.getSupplierId() == null && taskEntity.getDepartmentId() == null && taskEntity.getOwnerId() == null){
        		throw new BusinessException("该商机还未分配，无法进行提醒");
        	}
        }
	}
}
