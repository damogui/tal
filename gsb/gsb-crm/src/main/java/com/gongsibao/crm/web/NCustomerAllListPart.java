package com.gongsibao.crm.web;

import org.netsharp.communication.ServiceFactory;

import com.gongsibao.crm.base.INCustomerOperationLogService;
import com.gongsibao.crm.base.INCustomerService;

public class NCustomerAllListPart extends NCustomerBaseListPart {

	/**
	 * 开通会员功能
	 * 
	 * @param customerId
	 * @return
	 */
	public boolean openMember(Integer customerId) {

		INCustomerService customerService = ServiceFactory.create(INCustomerService.class);
		return customerService.openMember(customerId);
	}

	/**
	 * @Title: recordLookLog
	 * @Description: TODO(记录查看联系方式日志)
	 * @param: @param customerId
	 * @param: @param typeName
	 * @param: @return
	 * @return: boolean
	 * @throws
	 */
	public boolean recordLookLog(Integer customerId, String typeName) {

		INCustomerOperationLogService changeService = ServiceFactory.create(INCustomerOperationLogService.class);
		return changeService.recordLookLog(customerId, typeName);
	}
}
