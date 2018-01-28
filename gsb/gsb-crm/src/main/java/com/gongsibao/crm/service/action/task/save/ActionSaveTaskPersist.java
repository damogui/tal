package com.gongsibao.crm.service.action.task.save;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.base.IPersistableService;
import org.netsharp.util.ReflectManager;

import com.gongsibao.crm.service.NCustomerService;
import com.gongsibao.entity.crm.NCustomerTask;

/**
 * @author hw
 * 新增任务：保存
 */
public class ActionSaveTaskPersist implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		
		NCustomerTask task = (NCustomerTask) ctx.getItem();
		Class<?> superType = NCustomerService.class.getSuperclass();
		@SuppressWarnings("unchecked")
		IPersistableService<NCustomerTask> service = (IPersistableService<NCustomerTask>) ReflectManager.newInstance(superType);
		task = service.save(task);
		ctx.setItem(task);
	}
}
