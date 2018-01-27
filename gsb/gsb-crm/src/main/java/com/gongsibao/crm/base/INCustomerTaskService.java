package com.gongsibao.crm.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;

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
	 * （我的任务释放操作）修改业务员为空---进入公海中
	 * @param taskId 任务Id
	 * @return
	 */
	public int taskRelease(Integer taskId);
	/**
	 * 我的任务任务转移）修改服务商、部门、业务员
	 * @param taskId
	 * @param supplierId
	 * @param departmentId
	 * @param toUserId 去向的业务员Id
	 * @return
	 */
	public int taskTransfer(Integer taskId,Integer supplierId,Integer departmentId,Integer toUserId);
	
	
	/**
	 * 任务转移
	 */
	public Boolean transfer(String taskId,Integer supplierId,Integer departmentId,Integer toUserId);
	
	/**
	 * 抽查异常
	 */
	public Boolean abnormal(Integer taskId,Integer state,String content,Integer type);
	
	/**
	 * 任务分配
	 * @param taskIds 任务Ids
	 * @param supplierId 服务商
	 * @param departmentId 部门id
	 * @param toUserId 要分配的业务员Id
	 * @param allocationType 分配类型：1-"自动分配"、2-"手动分配"、3, "半自动分配"
	 * @return
	 */
	public Boolean allocation(String taskIds,Integer supplierId,Integer departmentId,Integer toUserId,Integer allocationType);
	
	/**
	 * 任务跟进
	 */
	public Boolean follow(NCustomerTaskFoolow taskFoolow);
	
	/**
	 * 任务收回(主管等操作)
	 */
	public Boolean regain(String taskIds,String content);
	
	/**
	 * 任务释放
	 */
	public Boolean release(Integer taskId);
	
	/**
	 * 任务回退(业务员主动操作)
	 */
	public Boolean rollback(Integer taskId,String content);
}