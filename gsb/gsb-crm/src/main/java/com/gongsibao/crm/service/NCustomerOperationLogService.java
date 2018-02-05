package com.gongsibao.crm.service;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerOperationLogService;
import com.gongsibao.entity.crm.NCustomerOperationLog;
import com.gongsibao.entity.crm.dic.ChangeType;

@Service
public class NCustomerOperationLogService extends SupplierPersistableService<NCustomerOperationLog> implements INCustomerOperationLogService {

	public NCustomerOperationLogService() {
		super();
		this.type = NCustomerOperationLog.class;
	}

	@Override
	public boolean recordLookLog(Integer customerId, String typeName) {

		NCustomerOperationLog entity = this.newInstance();
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