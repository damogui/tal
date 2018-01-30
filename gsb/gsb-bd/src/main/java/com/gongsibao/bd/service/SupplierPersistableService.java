package com.gongsibao.bd.service;

import org.netsharp.base.IPersistableService;
import org.netsharp.communication.Service;
import org.netsharp.entity.IPersistable;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.service.PersistableService;

import com.gongsibao.utils.SupplierSessionManager;

@Service
public class SupplierPersistableService<T extends IPersistable> extends PersistableService<T> implements IPersistableService<T> {

	public T newInstance() {
		
		T t = super.newInstance();
		t.set("supplierId", SupplierSessionManager.getSupplierId());
		t.set("departmentId", SupplierSessionManager.getDepartmentId());
		
		return t;
	}
}
