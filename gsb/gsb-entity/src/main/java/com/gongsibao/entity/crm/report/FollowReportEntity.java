package com.gongsibao.entity.crm.report;

import org.netsharp.core.annotations.Table;

@Table(isView=true, name = "")
public class FollowReportEntity extends BaseReportEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**   
	 * @Fields taskCount : TODO(全部商机数)   
	 */   
	private Integer taskCount = 0;
	
	/**   
	 * @Fields unfoolowCount : TODO(待跟进商机数)   
	 */   
	private Integer unfoolowCount = 0;
	
	/**   
	 * @Fields timeOutCount : TODO(超时商机数)   
	 */   
	private Integer timeOutCount = 0;
	
	/**   
	 * @Fields foolowCount : TODO(跟进商机数)   
	 */   
	private Integer foolowCount = 0;
	
	/**   
	 * @Fields qualityRisetaskCount : TODO(质量上升商机数)   
	 */   
	private Integer qualityRisetaskCount = 0;
	
	/**   
	 * @Fields qualityDeclinetaskCount : TODO(质量下降商机数)   
	 */   
	private Integer qualityDeclinetaskCount = 0;

	public Integer getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}

	public Integer getUnfoolowCount() {
		return unfoolowCount;
	}

	public void setUnfoolowCount(Integer unfoolowCount) {
		this.unfoolowCount = unfoolowCount;
	}

	public Integer getTimeOutCount() {
		return timeOutCount;
	}

	public void setTimeOutCount(Integer timeOutCount) {
		this.timeOutCount = timeOutCount;
	}

	public Integer getFoolowCount() {
		return foolowCount;
	}

	public void setFoolowCount(Integer foolowCount) {
		this.foolowCount = foolowCount;
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
	
}
