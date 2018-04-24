package com.gongsibao.crm.web;

import com.gongsibao.crm.base.ICustomerCompanyMapService;
import com.gongsibao.entity.crm.NCustomer;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.crm.base.INCustomerOperationLogService;
import com.gongsibao.crm.base.INCustomerService;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.FilterParameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NCustomerAllListPart extends NCustomerBaseListPart {

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
}
