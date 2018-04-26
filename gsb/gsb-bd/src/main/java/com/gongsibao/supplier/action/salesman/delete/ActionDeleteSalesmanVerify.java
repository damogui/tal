package com.gongsibao.supplier.action.salesman.delete;

import java.sql.Types;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.BusinessException;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.entity.supplier.Salesman;

public class ActionDeleteSalesmanVerify implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		Salesman entity = (Salesman) ctx.getItem();
		int taskCount = this.deleteVerify(entity);
		if (taskCount > 0) {// 校验能不能删除

			throw new BusinessException("该员工名下存在商机,不能删除");
		}
	}

	// /删除的时候校验员工下面是否存在商机
	private int deleteVerify(Salesman entity) {

		IPersister<Salesman> pm = PersisterFactory.create();
		String sqlQ = "SELECT  employee_id  FROM sp_salesman  WHERE  id=?";
		QueryParameters qpsQ = new QueryParameters();
		qpsQ.add("@id", entity.getId(), Types.INTEGER);

		Object employeeIdO = pm.executeScalar(sqlQ, qpsQ);
		if (employeeIdO == null) {

			return 0;
		} else {

			String sql = "SELECT  COUNT(1)  FROM n_crm_customer_task  WHERE owner_id=?";
			QueryParameters qps = new QueryParameters();
			qps.add("@owner_id", Integer.parseInt(employeeIdO.toString()), Types.INTEGER);

			int num = Integer.parseInt(pm.executeScalar(sql, qps).toString());
			return num;
		}
	}

}
