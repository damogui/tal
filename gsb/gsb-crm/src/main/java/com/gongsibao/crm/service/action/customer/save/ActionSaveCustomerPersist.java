package com.gongsibao.crm.service.action.customer.save;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.base.IPersistableService;
import org.netsharp.util.ReflectManager;

import com.gongsibao.crm.service.NCustomerService;
import com.gongsibao.entity.crm.NCustomer;

/**
 * @author hw
 * 客户保存：保存
 */
public class ActionSaveCustomerPersist  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		
		NCustomer customer = (NCustomer) ctx.getItem();
		Class<?> superType = NCustomerService.class.getSuperclass();
		@SuppressWarnings("unchecked")
		IPersistableService<NCustomer> service = (IPersistableService<NCustomer>) ReflectManager.newInstance(superType);

		customer = service.save(customer);
		ctx.setItem(customer);
	}

}
