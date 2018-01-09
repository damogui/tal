package com.gongsibao.entity.crm;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.crm.dic.NotifyType;

@Table(name="n_crm_task_notify",header="任务通知")
public class NCustomerTaskNotify extends Entity{

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
	
	@Column(name = "type", header = "通知类型")
	private NotifyType type;
	
	@Column(name = "content", size = 200, header = "跟进内容")
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

	public NotifyType getType() {
		return type;
	}

	public void setType(NotifyType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
