package com.gongsibao.crm.web;

import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.FormNavigation;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.crm.base.INCustomerOperationLogService;
import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerTask;

public class NCustomerFormPart extends FormPart{

	INCustomerService customerService = ServiceFactory.create(INCustomerService.class);
	INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
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

		INCustomerOperationLogService changeService = ServiceFactory.create(INCustomerOperationLogService.class);
		return changeService.recordLookLog(customerId, "手机号，座机号，QQ号，微信号，邮箱");
	}
	
	
	public FormNavigation bySwtCustomerId(String swtCustomerId) {

		FormNavigation navigation = this.createFormNavigation(id);
		IPersistable obj = customerService.bySwtCustomerId(swtCustomerId);
		if (obj == null) {
			
			navigation.Entity = this.newInstance(null);
		} else {
			navigation.Entity = obj;
		}

		return navigation;
	}
	
	public NCustomer save(NCustomer entity) {

		entity = customerService.create(entity);
		entity = customerService.byId(entity);
		return entity;
	}
	
	/**
	 * 当前登录人是否等于客户的商机所属业务员，若是允许创建否则返回相关的商机业务员
	 * @param customerId
	 * @return
	 */
	public String isHaveTask(int customerId){
		String ownerName = "";		
		List<NCustomerTask> taskList = taskService.getByCustomerId(customerId);
		for (NCustomerTask item : taskList) {
			if(!item.getOwnerId().equals(SessionManager.getUserId())){
				ownerName += item.getOwner().getName()+"、";
			}
		}
		if(ownerName.length()>0){
			ownerName = ownerName.substring(0,ownerName.length()-1);	
		}
		return ownerName;
	}
}