package com.gongsibao.crm.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.crm.NCustomerChange;

public interface INCustomerChangeService  extends IPersistableService<NCustomerChange> {

	/**   
	 * @Title: recordLookLog   
	 * @Description: TODO(记录查看操作日志)   
	 * @param: @param customerId
	 * @param: @param typeName
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	boolean recordLookLog(Integer customerId, String typeName);
}
