package com.gongsibao.crm.service;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerChangeService;
import com.gongsibao.entity.crm.NCustomerChange;
import com.gongsibao.entity.crm.dic.ChangeType;

@Service
public class NCustomerChangeService extends SupplierPersistableService<NCustomerChange> implements INCustomerChangeService {

	public NCustomerChangeService() {
		super();
		this.type = NCustomerChange.class;
	}

	@Override
	public boolean recordLookLog(Integer customerId, String typeName) {

		NCustomerChange entity = this.newInstance();
		{
			entity.toNew();
			entity.setCustomerId(customerId);
			entity.setChangeType(ChangeType.LOOK);
			entity.setFormUserId(entity.getCreatorId());
			String content = String.format("[%s]查看了%s", entity.getCreator(),typeName);
			entity.setContent(content);
		}
		this.save(entity);
		return true;
	}
}