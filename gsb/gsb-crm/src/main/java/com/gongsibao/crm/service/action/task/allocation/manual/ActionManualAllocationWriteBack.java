package com.gongsibao.crm.service.action.task.allocation.manual;

import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.AllocationState;
import com.gongsibao.entity.crm.dic.NAllocationType;

/**
 * @author hw 分配回写
 */
public class ActionManualAllocationWriteBack implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTask task = (NCustomerTask) ctx.getItem();
		Map<String, Object> getMap = ctx.getStatus();
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("n_crm_customer_task");
			updateSql.set("supplier_id", getMap.get("supplierId"));
			updateSql.set("department_id", getMap.get("departmentId"));
			updateSql.set("owner_id", getMap.get("toUserId"));
			updateSql.set("allocation_state", AllocationState.ALLOCATED.getValue());
			updateSql.set("allocation_type", NAllocationType.MANUAL.getValue());
			updateSql.where("id =" + task);
		}
		String cmdText = updateSql.toSQL();
		IPersister<NCustomerTask> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
	}

}
