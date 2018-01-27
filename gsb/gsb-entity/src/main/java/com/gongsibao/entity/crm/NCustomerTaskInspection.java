package com.gongsibao.entity.crm;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.crm.dic.TaskInspectionState;
import com.gongsibao.entity.crm.dic.TaskInspectionType;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;

@Table(name = "n_crm_task_inspection", header = "任务抽查处理记录")
public class NCustomerTaskInspection extends Entity{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 4684375504055933956L;

	@JsonIgnore
	@Reference(foreignKey = "customerId", header = "客户Id")
	private NCustomer customer;

	@Column(name = "customer_id", header = "客户")
	private Integer customerId = 0;

	@JsonIgnore
	@Reference(foreignKey = "taskId", header = "任务Id")
	private NCustomerTask task;

	@Column(name = "task_id", header = "任务")
	private Integer taskId = 0;
	
	@Column(name = "inspection_type", header = "抽查类型")
	private TaskInspectionType inspectionType;
	
	@Column(name = "inspection_state", header = "抽查异常状态")
	private TaskInspectionState inspectionState;
	
	@Column(name = "content", size = 100, header = "抽查内容")
	private String content;
	
	@Column(name = "supplier_id", header = "分配服务商Id")
	private Integer supplierId;

	@Reference(foreignKey = "supplierId", header = "分配服务商")
	private Supplier supplier;
	
	@Column(name = "department_id", header = "分配服务商部门Id")
	private Integer departmentId;

	@Reference(foreignKey = "departmentId", header = "分配服务商部门")
	private SupplierDepartment department;

	
	
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

	public TaskInspectionState getInspectionState() {
		return inspectionState;
	}

	public void setInspectionState(TaskInspectionState inspectionState) {
		this.inspectionState = inspectionState;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public TaskInspectionType getInspectionType() {
		return inspectionType;
	}

	public void setInspectionType(TaskInspectionType inspectionType) {
		this.inspectionType = inspectionType;
	}
	
}
