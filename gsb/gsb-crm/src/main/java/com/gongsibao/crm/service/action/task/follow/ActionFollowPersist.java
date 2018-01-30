package com.gongsibao.crm.service.action.task.follow;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.base.IPersistableService;
import org.netsharp.util.ReflectManager;

import com.gongsibao.crm.service.NCustomerTaskFoolowService;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;

/**
 * @author hw 跟进保存
 */
public class ActionFollowPersist implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTaskFoolow foolow = (NCustomerTaskFoolow) ctx.getItem();
		//supplierId，departmentId

		@SuppressWarnings("unchecked")
		IPersistableService<NCustomerTaskFoolow> service = (IPersistableService<NCustomerTaskFoolow>) ReflectManager.newInstance(NCustomerTaskFoolowService.class.getSuperclass());
		foolow = service.save(foolow);
		ctx.setItem(foolow);
	}
}
