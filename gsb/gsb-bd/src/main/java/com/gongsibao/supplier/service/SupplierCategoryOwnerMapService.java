package com.gongsibao.supplier.service;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.entity.supplier.SupplierCategoryOwnerMap;
import com.gongsibao.supplier.base.ISupplierCategoryOwnerMapService;

@Service
public class SupplierCategoryOwnerMapService extends SupplierPersistableService<SupplierCategoryOwnerMap> implements ISupplierCategoryOwnerMapService {

	public SupplierCategoryOwnerMapService() {
		super();
		this.type = SupplierCategoryOwnerMap.class;
	}
}