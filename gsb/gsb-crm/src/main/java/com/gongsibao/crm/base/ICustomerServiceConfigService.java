package com.gongsibao.crm.base;

import java.util.Date;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.crm.CustomerServiceConfig;
import com.gongsibao.entity.crm.dic.ServiceType;

public interface ICustomerServiceConfigService  extends IPersistableService<CustomerServiceConfig>{

	CustomerServiceConfig bySwtServiceId(String swtServiceId);
	
	ServiceType getTypeByEmployeeId(Integer employeeId);
	
	/**   
	 * @Title: updateLastUseDate   
	 * @Description: TODO(更新最后一次使用时间)   
	 * @param: @param employeeId
	 * @param: @param useDate
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	boolean updateLastUseDate(Integer employeeId,Date useDate);
}
