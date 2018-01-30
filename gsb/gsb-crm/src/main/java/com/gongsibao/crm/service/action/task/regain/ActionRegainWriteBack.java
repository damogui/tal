package com.gongsibao.crm.service.action.task.regain;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.MtableManager;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.utils.SupplierSessionManager;

/**
 * @author hw 收回回写
 */
public class ActionRegainWriteBack implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTask task = (NCustomerTask) ctx.getItem();
		IPersister<NCustomerTask> pm = PersisterFactory.create();

		//1.当前为售前时，收回到平台公海
		//2.当前为服务商员工时，收到到当前员工所在的部门下公海
		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(NCustomerTask.class).getTableName());
			updateBuilder.set("owner_id", null);
			
			Integer salesmanEmployeeId = SupplierSessionManager.getSalesmanEmployeeId();
			if(salesmanEmployeeId == null){
				
				updateBuilder.set("department_id", null);
				updateBuilder.set("supplier_id", null);
			}
			updateBuilder.where("id =" + task.getId());
		}
		
		pm.executeNonQuery(updateBuilder.toSQL(), null);
	}
}
