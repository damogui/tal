package com.gongsibao.supplier.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.entity.supplier.Supplier;

public interface ISupplierService  extends IPersistableService<Supplier>{

	/**   
	 * @Title: open   
	 * @Description: TODO(开户)   
	 * @param: @param supplierId
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	@Transaction
	Boolean openAccount(Integer supplierId);
	
	/**   
	 * @Title: close   
	 * @Description: TODO(销户)   
	 * @param: @param supplierId
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	@Transaction
	Boolean closeAccount(Integer supplierId);
	
	/**   
	 * @Title: hasChild   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param categoryId
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	Integer getSupplierCount(Integer categoryId);
}
