package com.gongsibao.entity.crm.home;

import org.netsharp.core.annotations.Table;

/**
 * 服务商级别的门户统计（业务员、部门）
 * @author Administrator
 *
 */
@Table(isView=true, name = "")
public class HomeSupplierEntity {

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
	 * 公海
	 */
	private Integer highSeasCount;
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
	
	/**   
	 * X类   
	 */   
	private Integer XCount = 0;
	
	/**   
	 * S类  
	 */   
	private Integer SCount = 0;
	
	/**   
	 * A0类   
	 */   
	private Integer A0Count = 0;
	
	/**   
	 * A1类   
	 */   
	private Integer A1Count = 0;
	
	/**   
	 * A2类   
	 */   
	private Integer A2Count = 0;
	
	/**   
	 * A3类   
	 */   
	private Integer A3Count = 0;
	
	/**   
	 * A4类   
	 */   
	private Integer A4Count = 0;
	
	/**   
	 * B1类   
	 */   
	private Integer B1Count = 0;
	
	/**   
	 * B2类   
	 */   
	private Integer B2Count = 0;
	
	/**   
	 * C1类   
	 */   
	private Integer C1Count = 0;
	
	/**   
	 * C2类   
	 */   
	private Integer C2Count = 0;
	
	/**   
	 * C3类   
	 */   
	private Integer C3Count = 0;
	
	/**   
	 * C4类   
	 */   
	private Integer C4Count = 0;
	
	/**   
	 * D1类   
	 */   
	private Integer D1Count = 0;
	
	/**   
	 * D2类   
	 */   
	private Integer D2Count = 0;
	
	
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

	public Integer getXCount() {
		return XCount;
	}

	public void setXCount(Integer xCount) {
		XCount = xCount;
	}

	public Integer getSCount() {
		return SCount;
	}

	public void setSCount(Integer sCount) {
		SCount = sCount;
	}

	public Integer getA0Count() {
		return A0Count;
	}

	public void setA0Count(Integer a0Count) {
		A0Count = a0Count;
	}

	public Integer getA1Count() {
		return A1Count;
	}

	public void setA1Count(Integer a1Count) {
		A1Count = a1Count;
	}

	public Integer getA2Count() {
		return A2Count;
	}

	public void setA2Count(Integer a2Count) {
		A2Count = a2Count;
	}

	public Integer getA3Count() {
		return A3Count;
	}

	public void setA3Count(Integer a3Count) {
		A3Count = a3Count;
	}

	public Integer getA4Count() {
		return A4Count;
	}

	public void setA4Count(Integer a4Count) {
		A4Count = a4Count;
	}

	public Integer getB1Count() {
		return B1Count;
	}

	public void setB1Count(Integer b1Count) {
		B1Count = b1Count;
	}

	public Integer getB2Count() {
		return B2Count;
	}

	public void setB2Count(Integer b2Count) {
		B2Count = b2Count;
	}

	public Integer getC1Count() {
		return C1Count;
	}

	public void setC1Count(Integer c1Count) {
		C1Count = c1Count;
	}

	public Integer getC2Count() {
		return C2Count;
	}

	public void setC2Count(Integer c2Count) {
		C2Count = c2Count;
	}

	public Integer getC3Count() {
		return C3Count;
	}

	public void setC3Count(Integer c3Count) {
		C3Count = c3Count;
	}

	public Integer getC4Count() {
		return C4Count;
	}

	public void setC4Count(Integer c4Count) {
		C4Count = c4Count;
	}

	public Integer getD1Count() {
		return D1Count;
	}

	public void setD1Count(Integer d1Count) {
		D1Count = d1Count;
	}

	public Integer getD2Count() {
		return D2Count;
	}

	public void setD2Count(Integer d2Count) {
		D2Count = d2Count;
	}

	public Integer getHighSeasCount() {
		return highSeasCount;
	}

	public void setHighSeasCount(Integer highSeasCount) {
		this.highSeasCount = highSeasCount;
	}
	
}
