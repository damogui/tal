package com.gongsibao.service;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.supplier.Salesman;

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
}
