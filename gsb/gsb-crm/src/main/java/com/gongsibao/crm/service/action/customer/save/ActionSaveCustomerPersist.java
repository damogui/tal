package com.gongsibao.crm.service.action.customer.save;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @author hw 客户保存：保存 1.把客户和商机分开保存 2.如果是新增状态的商机，则需要走商机的保存Action
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
		//获取最近一条商机来源Id,回写客户表
		if(taskList.size()>0){
			NCustomerTask getTask =  taskList.get(taskList.size()-1);
			if(getTask.getSourceId()!=null){ 
				Map<String, Object> setMap = new HashMap<String, Object> ();
	            setMap.put ("lastCustomerSourceId", getTask.getSourceId());
				ctx.setStatus(setMap);
			}
		}
		ctx.setItem(customer);
	}
}
