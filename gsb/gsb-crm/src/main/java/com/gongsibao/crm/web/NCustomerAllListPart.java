package com.gongsibao.crm.web;

import org.netsharp.communication.ServiceFactory;

import com.gongsibao.crm.base.INCustomerOperationLogService;
import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.crm.base.INCustomerTaskService;

public class NCustomerAllListPart extends NCustomerBaseListPart {
	INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
    /**
     * 开通会员功能
     *
     * @param customerId
     * @return
     */
    public boolean openMember(Integer customerId, Boolean isSendSms) {

        INCustomerService customerService = ServiceFactory.create(INCustomerService.class);
        return customerService.openMember(customerId, isSendSms);
    }

    /**
     * @throws
     * @Title: recordLookLog
     * @Description: TODO(记录查看联系方式日志)
     * @param: @param customerId
     * @param: @param typeName
     * @param: @return
     * @return: boolean
     */
    public boolean recordLookLog(Integer customerId, String typeName) {

        INCustomerOperationLogService changeService = ServiceFactory.create(INCustomerOperationLogService.class);
        return changeService.recordLookLog(customerId, typeName);
    }
    /**
	 * 根据客户id获取商机所属人的集合
	 * @param customerId
	 * @return
	 */
	public String isHaveTask(int customerId){		
		return taskService.getTaskNamesByCustomerId(customerId);
	}
}
