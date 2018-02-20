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
		if(!customer.getMobile().isEmpty()){
			getContact = customer.getMobile().substring(0,4) + "****" + customer.getMobile().substring(customer.getMobile().length()-3,customer.getMobile().length());
		}else if(!customer.getTelephone().isEmpty()){
			getContact = customer.getTelephone().substring(0,2) + "****" + customer.getTelephone().substring(customer.getTelephone().length()-2,customer.getTelephone().length());
		}else if(!customer.getWeixin().isEmpty()){
			getContact = customer.getWeixin().substring(0,2) + "****" + customer.getWeixin().substring(customer.getWeixin().length()-2,customer.getWeixin().length());
		}else if(!customer.getQq().isEmpty()){
			getContact = customer.getQq().substring(0,2) + "****" + customer.getQq().substring(customer.getQq().length()-2,customer.getQq().length());
		}
		return getContact;
	}
}
