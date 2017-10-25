package org.netsharp.organization.service.action;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.base.IPersistableService;
import org.netsharp.organization.entity.Organization;
import org.netsharp.organization.service.OrganizationService;
import org.netsharp.util.ReflectManager;

/*组织机构持久化
 *需要考虑服务父类支持的通用功能，比如编码机制等*/
public class ActionOrganizationSavePersist implements IAction {

	@Override
	public void execute(ActionContext ctx) {
		
		Class<?> superType = OrganizationService.class.getSuperclass();
		@SuppressWarnings("unchecked")
		IPersistableService<Organization> service = (IPersistableService<Organization>)ReflectManager.newInstance(superType);		
		
		Organization entity = (Organization)ctx.getItem();
		
		entity = service.save(entity);
		
		ctx.setItem(entity);
	}
}
