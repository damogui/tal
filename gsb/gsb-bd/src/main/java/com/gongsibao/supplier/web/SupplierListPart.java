package com.gongsibao.supplier.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.supplier.base.ISupplierService;

public class SupplierListPart extends ListPart{

	ISupplierService supplierService = ServiceFactory.create(ISupplierService.class);
	
	/**   
	 * @Title: open   
	 * @Description: TODO(开户)   
	 * @param: @param supplierId
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	public Boolean openAccount(Integer supplierId){
		
		return supplierService.openAccount(supplierId);
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
		
		return supplierService.closeAccount(supplierId);
	}
}
