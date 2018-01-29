package com.gongsibao.crm.base;

import java.util.List;
import java.util.Map;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

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
	@Transaction
	public int updateInspectionState(Integer taskId,Integer selectValue,String getNote);
	
	/**
	 * （我的任务释放操作）修改业务员为空---进入公海中
	 * @param taskId 任务Id
	 * @return
	 */
	@Transaction
	public int taskRelease(Integer taskId);
	
	/**
	 * 我的任务任务转移）修改服务商、部门、业务员
	 * @param taskId
	 * @param supplierId
	 * @param departmentId
	 * @param toUserId 去向的业务员Id
	 * @return
	 */
	@Transaction
	public int taskTransfer(Integer taskId,Integer supplierId,Integer departmentId,Integer toUserId);
	
	
	/**
	 * 任务转移
	 */
	@Transaction
	public Boolean transfer(String taskId,Integer supplierId,Integer departmentId,Integer toUserId);
	
	/**
	 * 抽查异常 
	 * @param taskId 任务Id
	 * @param state 1-"未抽查",2-"抽查正常",3-"抽查异常",4-"异常已处理"
	 * @param content
	 * @param type 1-"抽查",2-"处理"
	 * @return
	 */
	@Transaction
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
	@Transaction
	public Boolean allocation(String taskIds,Integer supplierId,Integer departmentId,Integer toUserId,Integer allocationType);
	
	/**
	 * 任务跟进
	 */
	@Transaction
	public Boolean follow(NCustomerTaskFoolow taskFoolow);
	
	/**
	 * 任务收回(主管等操作)
	 */
	@Transaction
	public Boolean regain(String taskIds,String content);
	
	/**
	 * 任务释放
	 */
	@Transaction
	public Boolean release(Integer taskId);
	
	/**
	 * 任务回退(业务员主动操作)
	 */
	@Transaction
	public Boolean rollback(Integer taskId,String content);
	
	/**自动分配
	 * @param taskId
	 * @return
	 */
	@Transaction
	public int autoAllot(Integer taskId);
	
	/**根据平台业务员id集合，获取该业务员的分配的任务个数
	 * @param employeeIdList
	 * @param type（0：当日已分配数 1：当周已分配数 2：XAB类客户任务数）
	 * @return
	 */
	public Map<Integer, Integer> getTaskCountByEmployeeIdList(List<Integer> employeeIdList, Integer type);
}