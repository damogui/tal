package com.gongsibao.crm.service.action.task.follow;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.crm.base.INCustomerTaskFoolowService;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;

/**
 * @author hw 跟进保存
 */
public class ActionFollowPersist implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTaskFoolow taskFoolow = (NCustomerTaskFoolow) ctx.getItem();
		INCustomerTaskFoolowService foolowService = ServiceFactory.create(INCustomerTaskFoolowService.class);
		foolowService.save(taskFoolow);
	}
}
