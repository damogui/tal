package com.gongsibao.bd.service;

import org.netsharp.base.IPersistableService;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.entity.IPersistable;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.service.PersistableService;

import com.gongsibao.supplier.base.ISalesmanService;

@Service
public class SupplierPersistableService<T extends IPersistable> extends PersistableService<T> implements IPersistableService<T> {

	public T newInstance() {
		
		T t = super.newInstance();
		
		Integer employeeId = SessionManager.getUserId();
		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		Integer supplierId = salesmanService.getSupplierId(employeeId);
		Integer departmentId = salesmanService.getDepartmentId(employeeId);
		
		t.set("supplierId", supplierId);
		t.set("departmentId", departmentId);
		return t;
	}
}
