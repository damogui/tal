package com.gongsibao.product.base;

import com.gongsibao.entity.product.Product;
import org.netsharp.base.IPersistableService;

import java.util.Collection;
import java.util.List;

public interface IProductService extends IPersistableService<Product> {
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

	List<Product> byIds(Collection<Integer> ids);
}