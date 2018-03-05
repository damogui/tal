package com.gongsibao.entity.crm.home;

import org.netsharp.core.annotations.Table;

/**
 * 售前门户统计
 * @author Administrator
 *
 */
@Table(isView=true, name = "")
public class HomeOperation {

	/**   
	 *新增客户数   
	 */   
	private Integer newCustomerCount = 0;
	/**
	 * 新增任务数
	 */
	private Integer newTasksCount;
	/**
	 * 未分配任务数
	 */
	private Integer undistributedTasksCount;
	/**
	 * 未启动任务数
	 */
	private Integer unStartTasksCount;
	/**
	 * 无任务客户数
	 */
	private Integer customerNotTaskCount;
	/**
	 * 无法签单任务数
	 */
	private Integer defeatedTasksCount;
	/**
	 * 抽查异常任务数
	 */
	private Integer checkAbnormalTasksCount;
	/**
	 * 公海
	 */
	private Integer highSeasCount;
	
	
	public Integer getNewCustomerCount() {
		return newCustomerCount;
	}
	public void setNewCustomerCount(Integer newCustomerCount) {
		this.newCustomerCount = newCustomerCount;
	}
	public Integer getNewTasksCount() {
		return newTasksCount;
	}
	public void setNewTasksCount(Integer newTasksCount) {
		this.newTasksCount = newTasksCount;
	}
	public Integer getUndistributedTasksCount() {
		return undistributedTasksCount;
	}
	public void setUndistributedTasksCount(Integer undistributedTasksCount) {
		this.undistributedTasksCount = undistributedTasksCount;
	}
	public Integer getUnStartTasksCount() {
		return unStartTasksCount;
	}
	public void setUnStartTasksCount(Integer unStartTasksCount) {
		this.unStartTasksCount = unStartTasksCount;
	}
	public Integer getCustomerNotTaskCount() {
		return customerNotTaskCount;
	}
	public void setCustomerNotTaskCount(Integer customerNotTaskCount) {
		this.customerNotTaskCount = customerNotTaskCount;
	}
	public Integer getDefeatedTasksCount() {
		return defeatedTasksCount;
	}
	public void setDefeatedTasksCount(Integer defeatedTasksCount) {
		this.defeatedTasksCount = defeatedTasksCount;
	}
	public Integer getCheckAbnormalTasksCount() {
		return checkAbnormalTasksCount;
	}
	public void setCheckAbnormalTasksCount(Integer checkAbnormalTasksCount) {
		this.checkAbnormalTasksCount = checkAbnormalTasksCount;
	}
	public Integer getHighSeasCount() {
		return highSeasCount;
	}
	public void setHighSeasCount(Integer highSeasCount) {
		this.highSeasCount = highSeasCount;
	}
}
