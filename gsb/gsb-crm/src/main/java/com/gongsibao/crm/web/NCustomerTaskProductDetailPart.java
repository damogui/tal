package com.gongsibao.crm.web;


import java.util.HashMap;
import java.util.Map;

import org.netsharp.panda.commerce.DetailPart;

public class NCustomerTaskProductDetailPart extends DetailPart{

	/**   
	 * @Title: queryByProducCategoryId1   
	 * @Description: TODO(根据一级分类查询二级分类)   
	 * @param: @param producCategoryId1
	 * @param: @return      
	 * @return: List<Dict>      
	 * @throws   
	 */
	public Map<Integer, String> queryByProducCategoryId1(Integer productCategoryId1){
		
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		
		return map;
	}
	
	/**   
	 * @Title: queryByProducCategoryId1   
	 * @Description: TODO(根据二级分类查询产品)   
	 * @param: @param producCategoryId1
	 * @param: @return      
	 * @return: List<Dict>      
	 * @throws   
	 */
	public Map<Integer, String> queryByProducCategoryId2(Integer productCategoryId1){
		
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		
		return map;
	}
	
	
	/**   
	 * @Title: queryByProducId   
	 * @Description: TODO(根据产品获取一级分类Id,二级分类Id)   
	 * @param: @param productId
	 * @param: @return      
	 * @return: Map<Integer,String>      
	 * @throws   
	 */
	public Map<Integer, String> queryByProducId(Integer productId){
		
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		
		return map;
	}
}
