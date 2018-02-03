package com.gongsibao.crm.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.crm.base.INCustomerChangeService;
import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.entity.crm.NCustomer;

public class NCustomerFormPart extends FormPart{

	INCustomerService customerService = ServiceFactory.create(INCustomerService.class);
	public NCustomer validationContactWay(Integer id,String contactWay,String type){
	
		return customerService.validationContactWay(id,contactWay, type);
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
	public boolean recordLookLog(Integer customerId) {

		INCustomerChangeService changeService = ServiceFactory.create(INCustomerChangeService.class);
		return changeService.recordLookLog(customerId, "手机号，座机号，QQ号，微信号，邮箱");
	}
}