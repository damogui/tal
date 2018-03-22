package com.gongsibao.product.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.product.Workflow;

public interface IWorkflowService extends IPersistableService<Workflow> {
	
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