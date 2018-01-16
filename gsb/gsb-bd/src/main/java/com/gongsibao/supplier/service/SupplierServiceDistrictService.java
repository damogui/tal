package com.gongsibao.supplier.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.supplier.SupplierServiceDistrict;
import com.gongsibao.supplier.base.ISupplierServiceDistrictService;


@Service
public class SupplierServiceDistrictService extends PersistableService<SupplierServiceDistrict> implements ISupplierServiceDistrictService {

	public SupplierServiceDistrictService() {
		super();
		this.type = SupplierServiceDistrict.class;
	}
}