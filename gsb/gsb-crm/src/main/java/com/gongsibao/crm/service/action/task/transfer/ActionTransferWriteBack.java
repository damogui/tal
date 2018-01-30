package com.gongsibao.crm.service.action.task.transfer;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.crm.NCustomerTask;

/**
 * @author hw 转移回写
 */
public class ActionTransferWriteBack implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTask task = (NCustomerTask) ctx.getItem();
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("n_crm_customer_task");
			updateSql.set("supplier_id", task.getSupplierId());
			updateSql.set("department_id", task.getDepartmentId());
			updateSql.set("owner_id", task.getOwnerId());
			updateSql.where("id =" + task.getId());
		}
		String cmdText = updateSql.toSQL();
		IPersister<NCustomerTask> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
	}
}
