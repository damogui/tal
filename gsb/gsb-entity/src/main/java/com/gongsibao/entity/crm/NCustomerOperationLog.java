package com.gongsibao.entity.crm;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.entity.Employee;

import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.OperationType;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;

@Table(name = "n_crm_customer_operation_log", orderBy = " create_time DESC", header = "商机流转")
public class NCustomerOperationLog extends Entity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 4684375504055933956L;

	@JsonIgnore
	@Reference(foreignKey = "customerId", header = "客户")
	private NCustomer customer;

	@Column(name = "customer_id", header = "客户")
	private Integer customerId;

	@JsonIgnore
	@Reference(foreignKey = "taskId", header = "客户")
	private NCustomerTask task;

	@Column(name = "task_id", header = "客户")
	private Integer taskId;

	@Column(name = "change_type", header = "操作类型")
	private ChangeType changeType;

	@Column(name = "operation_type", header = "操作类型")
	private OperationType type;

	@Column(name = "form_user_id", header = "来自业务员")
	private Integer formUserId;

	@Reference(foreignKey = "formUserId", header = "来自业务员")
	private Employee formUser;
	
	@Column(name = "form_department_id", header = "来自的部门")
	private Integer formDepartmentId;

	@Reference(foreignKey = "formDepartmentId", header = "来自的部门")
	private SupplierDepartment formSupplierDepartment;

	@Column(name = "form_supplier_id", header = "来自的平台")
	private Integer formSupplierId;

	@Reference(foreignKey = "formSupplierId", header = "来自的平台")
	private Supplier formSupplier;
	
	@Column(name = "to_user_id", header = "去向的业务员")
	private Integer toUserId;

	@Reference(foreignKey = "toUserId", header = "去向的业务员")
	private Employee toUser;

	@Column(name = "content", size = 200, header = "内容")
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

	public ChangeType getChangeType() {
		return changeType;
	}

	public void setChangeType(ChangeType changeType) {
		this.changeType = changeType;
	}

	public OperationType getType() {
		return type;
	}

	public void setType(OperationType type) {
		this.type = type;
	}
	
	public Integer getFormUserId() {
		return formUserId;
	}

	public void setFormUserId(Integer formUserId) {
		this.formUserId = formUserId;
	}

	public Employee getFormUser() {
		return formUser;
	}

	public void setFormUser(Employee formUser) {
		this.formUser = formUser;
	}

	public Integer getFormDepartmentId() {
		return formDepartmentId;
	}

	public void setFormDepartmentId(Integer formDepartmentId) {
		this.formDepartmentId = formDepartmentId;
	}

	public SupplierDepartment getFormSupplierDepartment() {
		return formSupplierDepartment;
	}

	public void setFormSupplierDepartment(SupplierDepartment formSupplierDepartment) {
		this.formSupplierDepartment = formSupplierDepartment;
	}

	public Integer getFormSupplierId() {
		return formSupplierId;
	}

	public void setFormSupplierId(Integer formSupplierId) {
		this.formSupplierId = formSupplierId;
	}

	public Supplier getFormSupplier() {
		return formSupplier;
	}

	public void setFormSupplier(Supplier formSupplier) {
		this.formSupplier = formSupplier;
	}

	public Integer getToUserId() {
		return toUserId;
	}

	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Employee getToUser() {
		return toUser;
	}

	public void setToUser(Employee toUser) {
		this.toUser = toUser;
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

}