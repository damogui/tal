package com.gongsibao.crm.service.action.task.allocation.manual;

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
		
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("n_crm_customer_task");
			updateSql.set("supplier_id", task.getSupplierId());
			updateSql.set("department_id", task.getDepartmentId());
			updateSql.set("owner_id", task.getOwnerId());
			updateSql.set("allocation_state", AllocationState.ALLOCATED.getValue());
			updateSql.set("allocation_type", NAllocationType.MANUAL.getValue());
			updateSql.set("distribut", true);
			updateSql.where("id =" + task.getId());
		}
		String cmdText = updateSql.toSQL();
		IPersister<NCustomerTask> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
	}

}