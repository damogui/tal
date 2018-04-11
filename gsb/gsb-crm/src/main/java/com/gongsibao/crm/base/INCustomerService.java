package com.gongsibao.crm.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.entity.crm.NCustomer;

public interface INCustomerService extends IPersistableService<NCustomer> {

	/**   
	 * @Title: create   
	 * @Description: TODO(创建客户)   
	 * @param: @param entity
	 * @param: @return      
	 * @return: NCustomer      
	 * @throws   
	 */
	NCustomer create(NCustomer entity);
	
	/**
	 * 开通会员
	 * 
	 * @param customerId
	 * @return
	 */
	@Transaction
	public boolean openMember(Integer customerId,Boolean isSendSms);

	/**
	 * @Title: validationContactWay
	 * @Description: TODO(根据联系方式查询客户)
	 * @param: @param id
	 * @param: @param contactWay
	 * @param: @param type
	 * @param: @return
	 * @return: NCustomer
	 * @throws
	 */
	NCustomer validationContactWay(Integer id, String contactWay, String type);

	/**
	 * @Title: bySwtCustomerId
	 * @Description: TODO(根据商务通Id查询)
	 * @param: @param swtCustomerId
	 * @param: @return
	 * @return: NCustomer
	 * @throws
	 */
	NCustomer bySwtCustomerId(String swtCustomerId);

	/**
	 * @Title: byContactWay
	 * @Description: TODO(根据联系方式查询)
	 * @param: @param contactWay
	 * @param: @param type
	 * @param: @return
	 * @return: NCustomer
	 * @throws
	 */
	NCustomer byContactWay(String contactWay, String type);

	/**
	 * @Title: bindSwtCustomerId
	 * @Description: TODO(绑定商务通Id)
	 * @param: @param swtCustomerId
	 * @param: @param customerId
	 * @param: @return
	 * @return: NCustomer
	 * @throws
	 */
	NCustomer bindSwtCustomerId(String swtCustomerId, int customerId);

	/**
	 * 根据id获取客户信息(只是查客户信息本身，提高效率)
	 * 
	 * @param taskId商机id
	 * @return
	 */
	NCustomer getById(Integer taskId);


	/**
	 * 通过电话查找客户信息
	 */
	NCustomer getByMobile(String mobile);

}
