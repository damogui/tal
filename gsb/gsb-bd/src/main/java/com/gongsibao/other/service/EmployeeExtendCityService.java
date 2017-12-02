package com.gongsibao.other.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.other.EmployeeExtendCity;
import com.gongsibao.other.base.IEmployeeExtendCityService;

@Service
public class EmployeeExtendCityService extends PersistableService<EmployeeExtendCity> implements IEmployeeExtendCityService {
	public EmployeeExtendCityService() {
		super();
		this.type = EmployeeExtendCity.class;
	}
}
