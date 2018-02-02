package com.gongsibao.entity.crm;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.crm.dic.QualityCategory;
import com.gongsibao.entity.crm.dic.TaskQualityProgress;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;

@Table(name = "n_crm_task_foolow", header = "任务跟进")
public class NCustomerTaskFoolow extends Entity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 4684375504055933956L;

	@JsonIgnore
	@Reference(foreignKey = "customerId", header = "客户Id")
	private NCustomer customer;

	@Column(name = "customer_id", header = "客户")
	private Integer customerId;

	@JsonIgnore
	@Reference(foreignKey = "taskId", header = "任务Id")
	private NCustomerTask task;

	@Column(name = "task_id", header = "任务")
	private Integer taskId;
	
	@Column(name = "quality_category", header = "质量分类")
	private QualityCategory qualityCategory;
	
	@Column(name = "quality_id", header = "质量Id")
	private Integer qualityId;
	
	@Reference(foreignKey = "qualityId", header = "质量")
	private NCustomerTaskQuality quality;
	
	@Column(name = "quality_progress", header = "质量进度")
	private TaskQualityProgress qualityProgress = TaskQualityProgress.INVARIABILITY;
	
	@Column(name = "next_foolow_time", header = "下次跟进时间")
	private Date nextFoolowTime;
	
	@Column(name = "content", size = 1000, header = "跟进内容")
	private String content;
	
	@Column(name = "estimate_amount", header = "估计签单金额")
	private Integer estimateAmount;
	
	@Column(name = "supplier_id", header = "服务商Id")
	private Integer supplierId;

	@Reference(foreignKey = "supplierId", header = "服务商")
	private Supplier supplier;
	
	@Column(name = "department_id", header = "服务商部门Id")
	private Integer departmentId;

	@Reference(foreignKey = "departmentId", header = "服务商部门")
	private SupplierDepartment department;
	
	public TaskQualityProgress getQualityProgress() {
		return qualityProgress;
	}

	public void setQualityProgress(TaskQualityProgress qualityProgress) {
		this.qualityProgress = qualityProgress;
	}

	public Integer getQualityId() {
		return qualityId;
	}

	public void setQualityId(Integer qualityId) {
		this.qualityId = qualityId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public SupplierDepartment getDepartment() {
		return department;
	}

	public void setDepartment(SupplierDepartment department) {
		this.department = department;
	}

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
