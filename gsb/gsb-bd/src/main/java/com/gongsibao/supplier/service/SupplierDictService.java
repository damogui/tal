package com.gongsibao.supplier.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.supplier.SupplierDict;
import com.gongsibao.supplier.base.ISupplierDictService;

@Service
public class SupplierDictService extends PersistableService<SupplierDict> implements ISupplierDictService {

	public SupplierDictService() {
		super();
		this.type = SupplierDict.class;
	}
}