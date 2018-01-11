package com.gongsibao.supplier.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.supplier.SupplierCategory;
import com.gongsibao.supplier.base.ISupplierCategoryService;

@Service
public class SupplierCategoryService extends PersistableService<SupplierCategory> implements ISupplierCategoryService {

	public SupplierCategoryService() {
		super();
		this.type = SupplierCategory.class;
	}
}