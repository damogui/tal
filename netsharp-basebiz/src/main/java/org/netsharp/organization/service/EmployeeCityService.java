package org.netsharp.organization.service;

import org.netsharp.communication.Service;
import org.netsharp.organization.base.IEmployeeCityService;
import org.netsharp.organization.entity.EmployeeCity;
import org.netsharp.service.PersistableService;

@Service
public class EmployeeCityService extends PersistableService<EmployeeCity> implements IEmployeeCityService {
	public EmployeeCityService() {
		super();
		this.type = EmployeeCity.class;
	}
}
