package com.gongsibao.supplier.web;

import org.netsharp.panda.commerce.ListPart;

public class SupplierListPart extends ListPart{

	/**   
	 * @Title: open   
	 * @Description: TODO(开户)   
	 * @param: @param supplierId
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	public Boolean openAccount(Integer supplierId){
		
		return true;
	}
	
	/**   
	 * @Title: close   
	 * @Description: TODO(销户)   
	 * @param: @param supplierId
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	public Boolean closeAccount(Integer supplierId){
		
		return true;
	}
}
