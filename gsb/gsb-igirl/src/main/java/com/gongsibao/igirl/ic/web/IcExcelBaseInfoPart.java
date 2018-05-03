package com.gongsibao.igirl.ic.web;

import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.entity.crm.NCustomer;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;

public class IcExcelBaseInfoPart extends FormPart {
    public NCustomer findByMobile(String mobile){
        INCustomerService customerService = ServiceFactory.create(INCustomerService.class);
        return customerService.getByMobile(mobile);
    }
}
