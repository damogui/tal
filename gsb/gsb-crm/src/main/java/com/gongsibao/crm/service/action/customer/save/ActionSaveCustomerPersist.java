package com.gongsibao.crm.service.action.customer.save;

import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.util.ReflectManager;

import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.crm.service.NCustomerService;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerTask;

/**
 * @author hw 客户保存：保存 1.把客户和任务分开保存 2.如果是新增状态的任务，则需要走任务的保存Action
 */
public class ActionSaveCustomerPersist implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomer customer = (NCustomer) ctx.getItem();
		Class<?> superType = NCustomerService.class.getSuperclass();
		@SuppressWarnings("unchecked")
		IPersistableService<NCustomer> service = (IPersistableService<NCustomer>) ReflectManager.newInstance(superType);

		List<NCustomerTask> taskList = customer.getTasks();
		customer.setTasks(null);
		customer = service.save(customer);

		INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
		for (NCustomerTask task : taskList) {

			task.setCustomerId(customer.getId());
			taskService.save(task);
		}
		ctx.setItem(customer);
	}
}
