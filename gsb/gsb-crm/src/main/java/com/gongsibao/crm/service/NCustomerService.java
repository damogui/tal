package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.entity.crm.NCustomer;

@Service
public class NCustomerService extends SupplierPersistableService<NCustomer> implements INCustomerService {

	public NCustomerService() {
		super();
		this.type = NCustomer.class;
	}

	@Override
	public int updateIsMember(Integer customerId) {
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("n_crm_customer");
			updateSql.set("is_member", 1);
			updateSql.where("id=" + customerId);
		}
		String cmdText = updateSql.toSQL();
		return this.pm.executeNonQuery(cmdText, null);
	}
}