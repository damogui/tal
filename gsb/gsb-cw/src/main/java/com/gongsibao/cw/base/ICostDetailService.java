package com.gongsibao.cw.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.cw.CostDetail;


public interface ICostDetailService extends IPersistableService<CostDetail> {

	
	/**
	 * 通过单据id和单据类型查询
	* @Title: getCostDetailItem  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param formId
	* @param @param formType
	* @param @return    参数  
	* @return List<CostDetail>    返回类型  
	* @throws
	 */
	public List<CostDetail> getCostDetailItem(Integer formId,Integer formType);
}
