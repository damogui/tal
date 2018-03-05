package com.gongsibao.entity.crm.home;

import org.netsharp.core.annotations.Table;

/**
 * 服务商级别的门户统计（业务员、部门）
 * @author Administrator
 *
 */
@Table(isView=true, name = "")
public class BaseHomeSupplierEntity {

	/**
	 * 新增任务数
	 */
	private Integer newTasksCount;
	/**
	 * 未启动任务数
	 */
	private Integer unStartTasksCount;
	/**
	 * 待跟进任务数
	 */
	private Integer unfoolowTasksCount;
	/**
	 * 超时任务数
	 */
	private Integer timeOutTasksCount;
	/**
	 * 跟进任务数
	 */
	private Integer foolowTasksCount;
	/**
	 * 异常未处理任务数
	 */
	private Integer exceptUntreatedTasksCount;
	/**   
	 * 质量上升任务数   
	 */   
	private Integer qualityRisetaskCount = 0;
	
	/**   
	 * 质量下降任务数   
	 */   
	private Integer qualityDeclinetaskCount = 0;
	/**   
	 * 今日预估签单额   
	 */   
	private Integer daySigningAmount = 0;

	/**   
	 * 今日预估回款额   
	 */   
	private Integer dayReturnedAmount = 0;
	/**   
	 * 本周预估签单额   
	 */   
	private Integer weekSigningAmount = 0;

	/**   
	 * 本周预估回款额   
	 */   
	private Integer weekReturnedAmount = 0;
	/**   
	 * 本月预估签单额   
	 */   
	private Integer monthSigningAmount = 0;

	/**   
	 * 本月预估回款额   
	 */   
	private Integer monthReturnedAmount = 0;

	
	
	public Integer getNewTasksCount() {
		return newTasksCount;
	}

	public void setNewTasksCount(Integer newTasksCount) {
		this.newTasksCount = newTasksCount;
	}

	public Integer getUnStartTasksCount() {
		return unStartTasksCount;
	}

	public void setUnStartTasksCount(Integer unStartTasksCount) {
		this.unStartTasksCount = unStartTasksCount;
	}

	public Integer getUnfoolowTasksCount() {
		return unfoolowTasksCount;
	}

	public void setUnfoolowTasksCount(Integer unfoolowTasksCount) {
		this.unfoolowTasksCount = unfoolowTasksCount;
	}

	public Integer getTimeOutTasksCount() {
		return timeOutTasksCount;
	}

	public void setTimeOutTasksCount(Integer timeOutTasksCount) {
		this.timeOutTasksCount = timeOutTasksCount;
	}

	public Integer getFoolowTasksCount() {
		return foolowTasksCount;
	}

	public void setFoolowTasksCount(Integer foolowTasksCount) {
		this.foolowTasksCount = foolowTasksCount;
	}

	public Integer getExceptUntreatedTasksCount() {
		return exceptUntreatedTasksCount;
	}

	public void setExceptUntreatedTasksCount(Integer exceptUntreatedTasksCount) {
		this.exceptUntreatedTasksCount = exceptUntreatedTasksCount;
	}

	public Integer getQualityRisetaskCount() {
		return qualityRisetaskCount;
	}

	public void setQualityRisetaskCount(Integer qualityRisetaskCount) {
		this.qualityRisetaskCount = qualityRisetaskCount;
	}

	public Integer getQualityDeclinetaskCount() {
		return qualityDeclinetaskCount;
	}

	public void setQualityDeclinetaskCount(Integer qualityDeclinetaskCount) {
		this.qualityDeclinetaskCount = qualityDeclinetaskCount;
	}

	public Integer getDaySigningAmount() {
		return daySigningAmount;
	}

	public void setDaySigningAmount(Integer daySigningAmount) {
		this.daySigningAmount = daySigningAmount;
	}

	public Integer getDayReturnedAmount() {
		return dayReturnedAmount;
	}

	public void setDayReturnedAmount(Integer dayReturnedAmount) {
		this.dayReturnedAmount = dayReturnedAmount;
	}

	public Integer getWeekSigningAmount() {
		return weekSigningAmount;
	}

	public void setWeekSigningAmount(Integer weekSigningAmount) {
		this.weekSigningAmount = weekSigningAmount;
	}

	public Integer getWeekReturnedAmount() {
		return weekReturnedAmount;
	}

	public void setWeekReturnedAmount(Integer weekReturnedAmount) {
		this.weekReturnedAmount = weekReturnedAmount;
	}

	public Integer getMonthSigningAmount() {
		return monthSigningAmount;
	}

	public void setMonthSigningAmount(Integer monthSigningAmount) {
		this.monthSigningAmount = monthSigningAmount;
	}

	public Integer getMonthReturnedAmount() {
		return monthReturnedAmount;
	}

	public void setMonthReturnedAmount(Integer monthReturnedAmount) {
		this.monthReturnedAmount = monthReturnedAmount;
	}
}
