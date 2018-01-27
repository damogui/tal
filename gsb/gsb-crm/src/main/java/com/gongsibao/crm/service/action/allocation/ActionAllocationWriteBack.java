package com.gongsibao.crm.service.action.allocation;

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
		NCustomerTask getEntity = (NCustomerTask)ctx.getItem(); 
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("n_crm_customer_task");
			updateSql.set("supplier_id", getEntity.getSupplierId());
			updateSql.set("department_id", getEntity.getDepartmentId());
			updateSql.set("owner_id", getEntity.getOwnerId());
			updateSql.set("allocation_statee", 2);
			updateSql.where("id=" + getEntity.getId());
		}
		String cmdText = updateSql.toSQL();
		pm.executeNonQuery(cmdText, null);
	}

}
