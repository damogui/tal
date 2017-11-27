package com.gongsibao.crm.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.crm.CustomerServiceConfig;
import com.gongsibao.entity.crm.dic.ServiceType;

public interface ICustomerServiceConfigService  extends IPersistableService<CustomerServiceConfig>{

	CustomerServiceConfig bySwtServiceId(String swtServiceId);
	
	ServiceType getTypeByEmployeeId(Integer employeeId);
}
