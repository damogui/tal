package com.gongsibao.entity.crm;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="crm_customer_follow",header="跟进日志")
public class CustomerFollow extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -1841356716602770525L;

	@Column(name="customer_id",header="")
    private Integer customerId;
	
	@JsonIgnore
    @Reference(foreignKey="customerId",header="",primaryKey="pkid")
    private Customer customer;
	
    private String content;
    
    @Column(name="follow_user_id",header="")
    private Integer creatorId;
    
    @Column(name="follow_time",header="")
    private Date createTime;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}