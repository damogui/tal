package com.gongsibao.service;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.supplier.dict.SupplierType;

public interface ISalesmanService extends IPersistableService<Salesman> {

	/**   
	 * @Title: getReceiving   
	 * @Description: TODO(获取业务员的接单状态)   
	 * @param: @param employeeId
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	boolean getReceiving(Integer employeeId);
	
	/**   
	 * @Title: getSupplierType   
	 * @Description: TODO(获取当前登录人员的服务商类型)   
	 * @param: @param employeeId
	 * @param: @return      
	 * @return: SupplierType      
	 * @throws   
	 */
	SupplierType getSupplierType(Integer employeeId);
}
