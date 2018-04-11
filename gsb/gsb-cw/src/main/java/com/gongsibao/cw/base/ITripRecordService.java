package com.gongsibao.cw.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.cw.TripRecord;


public interface ITripRecordService extends IPersistableService<TripRecord>{

	/**
	 * 通过报销id查询行程明细
	* @Title: getTripItem  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param expenseId
	* @param @return    参数  
	* @return List<TripRecord>    返回类型  
	* @throws
	 */
	public List<TripRecord> getTripItem(Integer expenseId);
}
