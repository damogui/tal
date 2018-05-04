package com.gongsibao.cw.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.cw.SubsidyRecord;

public interface ISubsidyRecordService extends IPersistableService<SubsidyRecord>{

	/**
	 * 通过报销id查询补助明细
	* @Title: getSubsidyItem  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param expenseId
	* @param @return    参数  
	* @return List<SubsidyRecord>    返回类型  
	* @throws
	 */
	public List<SubsidyRecord> getSubsidyItem(Integer expenseId);
}
