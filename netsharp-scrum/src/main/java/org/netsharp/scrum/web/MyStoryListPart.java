package org.netsharp.scrum.web;

import org.netsharp.authorization.UserPermission;
import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.core.Oql;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.commerce.ListPart;
import org.netsharp.util.StringManager;

import java.util.List;

public class MyStoryListPart extends ListPart {

	@Override
	public List<?> doQuery(Oql oql) {
		String filter = oql.getFilter();
		String defaultFilter = "1=2";
		UserPermission up = UserPermissionManager.getUserPermission();
		if (up != null) {
			Employee employee = up.getEmployee();
			if (StringManager.isNullOrEmpty(filter)) {
				defaultFilter = "iteration.isCurrent=1 or (Project.ownerId=" + employee.getId()+" and Project.idCreator="+employee.getId()+")";
			} else {
				defaultFilter = " and iteration.isCurrent=1 or (Project.ownerId=" + employee.getId()+" and Project.idCreator="+employee.getId()+")";
			}
		}

		oql.setFilter(filter + defaultFilter);
		return super.doQuery(oql);
	}
}
