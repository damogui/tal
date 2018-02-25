package com.gongsibao.crm.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.crm.service.action.task.transfer.ProcessNoticeEnum;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;

public interface INCustomerTaskService extends IPersistableService<NCustomerTask> {

	/**
	 * 修改抽查异常状态
	 * 
	 * @param taskId
	 *            任务Id
	 * @param selectValue
	 *            异常状态类型
	 * @param getNote
	 *            异常备注
	 * @return
	 */
	@Transaction
	public int updateInspectionState(Integer taskId, Integer selectValue, String getNote);

	/**
	 * （我的任务释放操作）修改业务员为空---进入公海中
	 * 
	 * @param taskId
	 *            任务Id
	 * @return
	 */
	@Transaction
	public int taskRelease(Integer taskId);

	/**   
	 * @Title: batchTransfer   
	 * @Description: TODO(批量转移)   
	 * @param: @param taskIdArray
	 * @param: @param supplierId
	 * @param: @param departmentId
	 * @param: @param toUserId
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	@Transaction
	public Boolean batchTransfer(String[] taskIdArray, Integer supplierId, Integer departmentId, Integer toUserId);
	
	/**
	 * 任务转移
	 */
	@Transaction
	public Boolean transfer(Integer taskId, Integer supplierId, Integer departmentId, Integer toUserId, Map<ProcessNoticeEnum,Map<Integer, Integer>> noticeMap, boolean isNotify);

	/**
	 * 抽查异常
	 * 
	 * @param taskId
	 *            任务Id
	 * @param state
	 *            1-"未抽查",2-"抽查正常",3-"抽查异常",4-"异常已处理"
	 * @param content
	 * @param type
	 *            1-"抽查",2-"处理"
	 * @return
	 */
	@Transaction
	public Boolean abnormal(Integer taskId, Integer state, String content, Integer type);

	/**
	 * 任务分配
	 * 
	 * @param taskIds
	 *            任务Ids
	 * @param supplierId
	 *            服务商
	 * @param departmentId
	 *            部门id
	 * @param toUserId
	 *            要分配的业务员Id
	 * @param allocationType
	 *            分配类型：1-"自动分配"、2-"手动分配"、3, "半自动分配"
	 * @return
	 */
	@Transaction
	public Boolean batchAllocation(String[] taskIdArray, Integer supplierId, Integer departmentId, Integer toUserId);

	/**   
	 * @Title: allocation   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param taskId
	 * @param supplierId
	 * @param departmentId
	 * @param toUserId
	 * @param alloCount 分配的个数
	 * @param isNotify 当时批量分配时，判断是否已经发送过通知
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	@Transaction
	public Boolean allocation(Integer taskId, Integer supplierId, Integer departmentId, Integer toUserId, int alloCount, boolean isNotify);

	/**
	 * 任务跟进
	 * @param taskFoolow
	 * @param originalQualityId 没更新前质量Id（用于判断是否改变，发送通知用）
	 * @return
	 */
	@Transaction
	public Boolean follow(NCustomerTaskFoolow taskFoolow,Integer originalQualityId);

	/**
	 * 任务收回(主管等操作)
	 */
	@Transaction
	public Boolean batchRegain(String[] taskIdArray, String content);

	/**
	 * @Title: regain
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param taskId
	 * @param: @param content
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	@Transaction
	public Boolean regain(Integer taskId, String content);

	/**
	 * 任务回退(业务员主动操作)
	 */
	@Transaction
	public Boolean rollback(Integer taskId, String content);

	/**
	 * 自动分配
	 * 
	 * @param taskId
	 * @return
	 */
	@Transaction
	public int autoAllot(Integer taskId);

	/**
	 * 根据平台业务员id集合，获取该业务员的分配的任务个数
	 * 
	 * @param employeeIdList
	 * @param type
	 *            （0：当日已分配数 1：当周已分配数 2：XAB类客户任务数）
	 * @return
	 */
	public Map<Integer, Integer> getTaskCountByEmployeeIdList(List<Integer> employeeIdList, Integer type);
	
	
	/**
	 * 获取公海中未分配任务的服务商/部门，以及对应的条数（待分配通知-Job用）
	 * @return
	 */
	public Map<Integer, Integer> getAssignmentCountBySeas();
	
	/**
	 * 获取当前时间等于任务下次预约时间，的任务集合（待跟进提醒-Job用）。
	 * @param time
	 * @return
	 */
	public List<NCustomerTask> getUnFoolowList(Date time);
	
	/**
	 * 获取当前时间大于任务下次预约时间，的任务集合（超时通知提醒-Job用）。
	 * @param time
	 * @return
	 */
	public List<NCustomerTask> getTimeOutList(Date time);
	
	/**
	 * 获取业务员在规定的时间内未跟进时，的任务集合（未启动提醒-Job用）。
	 * @param time
	 * @return
	 */
	public List<NCustomerTask> getNoStartList(Date time);

	/*
	* 修改任务的分配状态
	* */
	@Transaction
	public int updateAllocationState(NCustomerTask task);
}