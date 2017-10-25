package org.netsharp.organization.service.action;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.organization.base.IAuthorizationPrincipalService;
import org.netsharp.organization.entity.Organization;
import org.netsharp.organization.entity.OrganizationEmployee;

/*组织机构保存后置条件：同步岗位信息*/
public class ActionOrganizationSavePost implements IAction {

	@Override
	public void execute(ActionContext ctx) {
		
		Organization entity = (Organization)ctx.getItem();
	    EntityState state = ctx.getState();
	    
		if (state == EntityState.New) {
			IAuthorizationPrincipalService principalService = ServiceFactory.create(IAuthorizationPrincipalService.class);
			principalService.addByPost(entity);
		} else if (state == EntityState.Persist) {

		} else if (state == EntityState.Deleted) {
			entity.toDeleted();
			if (entity.getEmployees() != null && entity.getEmployees().size() > 0) {
				for (OrganizationEmployee oe : entity.getEmployees()) {
					oe.toDeleted();
				}
			}
			IAuthorizationPrincipalService principalService = ServiceFactory.create(IAuthorizationPrincipalService.class);
			principalService.deleteByPost(entity);
		} else {
		}
	}
}
