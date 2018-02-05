package com.gongsibao.crm.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.crm.NCustomerOperationLog;

public interface INCustomerOperationLogService  extends IPersistableService<NCustomerOperationLog> {

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
