package com.gongsibao.product.base;

import java.util.Collection;
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

	/**
	 * @Title: findCityIdsByProductIdAndOrganizationIds
	 * @Description: TODO(根据产品Id、部门Id查询服务项目)
	 * @param: @param productId
	 * @param: @param organizationIds
	 * @param: @return
	 * @return: List<Integer>
	 * @throws
	 */
	List<Integer> findCityIdsByProductIdAndOrganizationIds(int productId, Collection<Integer> organizationIds);
	/**
	 * @Title: findCityIdsByProductIdAndOrganizationIds
	 * @Description: TODO(根据产品Id、地区Id查询服务项目)
	 * @param: @param productId
	 * @param: @param cityId
	 * @param: @return
	 * @return: List<Integer>
	 * @throws
	 */
	List<Integer> findProductPropertyIds(int productId, int cityId);
}