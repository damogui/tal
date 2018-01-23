package com.gongsibao.crm.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.crm.NCustomerTask;

public interface INCustomerTaskService  extends IPersistableService<NCustomerTask> {

	/**
	 * 修改抽查异常状态
	 * @param taskId 任务Id
	 * @param selectValue 异常状态类型
	 * @param getNote 异常备注
	 * @return
	 */
	public int updateInspectionState(Integer taskId,Integer selectValue,String getNote);
	/**
	 * 修改最后跟进人状态 为空进入公海中
	 * @param taskId 任务Id
	 * @return
	 */
	public int updateLastFoolowUser(Integer taskId);
}
