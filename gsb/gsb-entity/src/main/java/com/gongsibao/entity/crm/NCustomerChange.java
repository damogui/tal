package com.gongsibao.entity.crm;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.entity.Employee;

import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.OperationType;

@Table(name = "n_crm_task_change", header = "任务流转")
public class NCustomerChange extends Entity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 4684375504055933956L;

	@Reference(foreignKey = "customerId", header = "客户")
	private NCustomer customer;

	@Column(name = "customer_id", header = "客户")
	private Integer customerId = 0;

	@Reference(foreignKey = "taskId", header = "客户")
	private NCustomerTask task;

	@Column(name = "task_id", header = "客户")
	private Integer taskId = 0;

	@Column(name = "change_type", header = "流转类型")
	private ChangeType changeType;

	@Column(name = "operation_type", header = "操作类型")
	private OperationType type;

	@Column(name = "form_user_id", header = "来自")
	private Integer formUserId = 0;
	
	@Reference(foreignKey = "formUserId", header = "客户")
	private Employee formUser;
	
	@Column(name = "to_user_id", header = "去向")
	private Integer toUserId = 0;
	
	@Reference(foreignKey = "toUserId", header = "客户")
	private Employee toUser;
	
	@Column(name = "content", size = 200, header = "内容")
	private String content;

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

	public Employee getFormUser() {
		return formUser;
	}

	public void setFormUser(Employee formUser) {
		this.formUser = formUser;
	}

	public Employee getToUser() {
		return toUser;
	}

	public void setToUser(Employee toUser) {
		this.toUser = toUser;
	}
}