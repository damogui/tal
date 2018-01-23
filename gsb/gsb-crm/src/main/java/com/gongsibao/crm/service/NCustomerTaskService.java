package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerTask;

@Service
public class NCustomerTaskService extends SupplierPersistableService<NCustomerTask> implements INCustomerTaskService {
	public NCustomerTaskService() {
		super();
		this.type = NCustomerTask.class;
	}

	@Override
	public int updateInspectionState(Integer taskId, Integer selectValue,
			String getNote) {
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("n_crm_customer_task");
			updateSql.set("inspection_state", selectValue);
			updateSql.set("memoto", getNote);
			updateSql.where("id=" + taskId);
		}
		String cmdText = updateSql.toSQL();
		return this.pm.executeNonQuery(cmdText, null);
	}

	@Override
	public int updateLastFoolowUser(Integer taskId) {
		String cmdText = "UPDATE n_crm_customer_task SET last_foolow_user_id = NULL where id="+taskId;
		return this.pm.executeNonQuery(cmdText, null);
	}
}