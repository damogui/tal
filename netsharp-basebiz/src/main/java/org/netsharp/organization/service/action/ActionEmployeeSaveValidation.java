package org.netsharp.organization.service.action;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.BusinessException;
import org.netsharp.core.MtableManager;
import org.netsharp.core.QueryParameters;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;

public class ActionEmployeeSaveValidation implements IAction {

	@Override
	public void execute(ActionContext ctx) {
		
		Employee entity = (Employee)ctx.getItem();
		IPersister<Employee> pm = PersisterFactory.create();
		
		List<String> filters = new ArrayList<String>();
		QueryParameters qps = new QueryParameters();

		filters.add("mobile=?");
		qps.add("mobile", entity.getMobile(), Types.VARCHAR);

		if (entity.getId() != null) {
			filters.add("id != ?");
			qps.add("id", entity.getId(), Types.INTEGER);
		}

		String cmdText = "select count(0) from " + MtableManager.getMtable(Employee.class).getTableName() + " where " + StringManager.join(" and ", filters);

		int count = pm.executeInt(cmdText, qps);

		if (count > 0) {
			throw new BusinessException("手机号码已经存在!");
		}
	}

}
