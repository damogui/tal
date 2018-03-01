package com.gongsibao.product.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.product.Price;

public interface IPriceService extends IPersistableService<Price> {
	
	/**   
	 * @Title: queryServicePriceItem   
	 * @Description: TODO(根据产品Id、地区Id查询服务项目)   
	 * @param: @param productId
	 * @param: @param cityId
	 * @param: @return      
	 * @return: List<Price>      
	 * @throws   
	 */
	List<Price> queryServicePriceItem(Integer productId,Integer cityId);
}