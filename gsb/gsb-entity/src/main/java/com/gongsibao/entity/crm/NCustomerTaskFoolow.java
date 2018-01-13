package com.gongsibao.entity.crm;

import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.crm.dic.CustomerFollowStatus;
import com.gongsibao.entity.crm.dic.QualityCategory;

@Table(name = "n_crm_task_foolow", header = "任务跟进")
public class NCustomerTaskFoolow extends Entity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 4684375504055933956L;

	@Reference(foreignKey = "customerId", header = "客户Id")
	private NCustomer customer;

	@Column(name = "customer_id", header = "客户")
	private Integer customerId = 0;

	@Reference(foreignKey = "taskId", header = "任务Id")
	private NCustomerTask task;

	@Column(name = "task_id", header = "任务")
	private Integer taskId = 0;

	@Column(name = "foolow_status", header = "跟进状态")
	private CustomerFollowStatus foolowStatus;
	
	@Column(name = "quality_category", header = "质量分类")
	private QualityCategory qualityCategory;
	
	@Column(name = "quality", header = "质量")
	private NCustomerTaskQuality quality;
	
	@Column(name = "next_foolow_time", header = "下次跟进时间")
	private Date nextFoolowTime;
	
	@Column(name = "content", size = 100, header = "跟进内容")
	private String content;
	
	@Column(name = "estimate_amount", header = "估计签单金额")
	private Integer estimateAmount = 0;

	public NCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(NCustomer customer) {
		this.customer = customer;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public NCustomerTask getTask() {
		return task;
	}

	public void setTask(NCustomerTask task) {
		this.task = task;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public CustomerFollowStatus getFoolowStatus() {
		return foolowStatus;
	}

	public void setFoolowStatus(CustomerFollowStatus foolowStatus) {
		this.foolowStatus = foolowStatus;
	}

	public QualityCategory getQualityCategory() {
		return qualityCategory;
	}

	public void setQualityCategory(QualityCategory qualityCategory) {
		this.qualityCategory = qualityCategory;
	}

	public NCustomerTaskQuality getQuality() {
		return quality;
	}

	public void setQuality(NCustomerTaskQuality quality) {
		this.quality = quality;
	}

	public Date getNextFoolowTime() {
		return nextFoolowTime;
	}

	public void setNextFoolowTime(Date nextFoolowTime) {
		this.nextFoolowTime = nextFoolowTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getEstimateAmount() {
		return estimateAmount;
	}

	public void setEstimateAmount(Integer estimateAmount) {
		this.estimateAmount = estimateAmount;
	}
}
