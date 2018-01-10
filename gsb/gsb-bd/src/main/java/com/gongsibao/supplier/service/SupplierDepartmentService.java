package com.gongsibao.supplier.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.supplier.base.ISupplierDepartmentService;

@Service
public class SupplierDepartmentService extends PersistableService<SupplierDepartment> implements ISupplierDepartmentService {

	public SupplierDepartmentService() {
		super();
		this.type = SupplierDepartment.class;
	}
}