package com.gongsibao.crm.web.home;

public class PortalStatisticDTO {

	/**
	 * 获取新增商机数
	 */
	private Integer newTasksCount;
	/**
	 * 获取未启动商机数
	 */
	private Integer unStartTasksCount;
	/**
	 * 获取待跟进商机数
	 */
	private Integer unfoolowTasksCount;
	/**
	 * 获取超时商机数
	 */
	private Integer timeOutTasksCount;
	/**
	 * 获取异常未处理商机数
	 */
	private Integer exceptUntreatedTasksCount;
	/**
	 * 获取公海商机数
	 */
	private Integer highSeasCount;
	
	
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
	public Integer getExceptUntreatedTasksCount() {
		return exceptUntreatedTasksCount;
	}
	public void setExceptUntreatedTasksCount(Integer exceptUntreatedTasksCount) {
		this.exceptUntreatedTasksCount = exceptUntreatedTasksCount;
	}
	public Integer getHighSeasCount() {
		return highSeasCount;
	}
	public void setHighSeasCount(Integer highSeasCount) {
		this.highSeasCount = highSeasCount;
	}
}
