package com.gongsibao.product.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.product.ProductPackage;

public interface IProductPackageService extends IPersistableService<ProductPackage> {
	
	/**   
	 * @Title: updateEnabled   
	 * @Description: TODO(更新产品方案启用状态)   
	 * @param: @param id
	 * @param: @param state
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	Boolean updateEnabled(Integer id, Boolean state);
}