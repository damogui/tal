package com.gongsibao.crm.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.crm.CustomerServiceConfig;

public interface ICustomerServiceConfigService  extends IPersistableService<CustomerServiceConfig>{

	CustomerServiceConfig bySwtServiceId(String swtServiceId);
}
