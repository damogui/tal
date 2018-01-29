package com.gongsibao.crm.service.action.task.allocation;

import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.crm.NCustomerTask;

/**
 * @author hw
 * 分配回写
 */
public class ActionAllocationWriteBack implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		IPersister<NCustomerTask> pm = PersisterFactory.create();
		Map<String,Object> getMap = ctx.getStatus();
		String  taskIds = getMap.get("taskIds").toString().replace("_", ",");
		
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("n_crm_customer_task");
			updateSql.set("supplier_id", getMap.get("supplierId"));
			updateSql.set("department_id", getMap.get("departmentId"));
			updateSql.set("owner_id", getMap.get("toUserId"));
			updateSql.set("allocation_state", 2);
			updateSql.set("allocation_type", getMap.get("allocationType"));
			updateSql.where("id in(" + taskIds + ")");
		}
		String cmdText = updateSql.toSQL();
		pm.executeNonQuery(cmdText, null);
	}

}
