package com.gongsibao.utils;

import com.gongsibao.entity.crm.NCustomer;

/**
 * 处理通知文案中的联系方式
 * @author Administrator
 *
 */
public class NCustomerContact {

	/**
	 * 获取联系方式
	 * @param customer
	 * @return
	 */
	public static String handleContact(NCustomer customer){
		String getContact = "";
		if(customer.getMobile() != null && !customer.getMobile().isEmpty()){
			getContact = customer.getMobile();
			//getContact = customer.getMobile().substring(0,4) + "****" + customer.getMobile().substring(customer.getMobile().length()-3,customer.getMobile().length());
		}else if(customer.getTelephone() != null && !customer.getTelephone().isEmpty()){
			getContact = customer.getTelephone();
			//getContact = customer.getTelephone().substring(0,1) + "****" + customer.getTelephone().substring(customer.getTelephone().length()-1,customer.getTelephone().length());
		}else if(customer.getWeixin() != null && !customer.getWeixin().isEmpty()){
			getContact = customer.getWeixin();
			//getContact = customer.getWeixin().substring(0,1) + "****" + customer.getWeixin().substring(customer.getWeixin().length()-1,customer.getWeixin().length());
		}else if(customer.getQq() != null && !customer.getQq().isEmpty()){
			getContact = customer.getQq();
			//getContact = customer.getQq().substring(0,1) + "****" + customer.getQq().substring(customer.getQq().length()-1,customer.getQq().length());
		}
		return getContact;
	}
}
