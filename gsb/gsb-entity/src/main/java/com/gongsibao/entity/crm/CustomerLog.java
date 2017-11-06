package com.gongsibao.entity.crm;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="crm_customer_log")
public class CustomerLog extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6444041205904741289L;
	@Column(name="customer_id")
    private Integer customerId;
    @Column(name="op_type")
    private Integer opType;
    @Column(name="user_id")
    private Integer userId;
    @Column(name="organization_id")
    private Integer organizationId;
    @Column(name="add_user_id")
    private Integer addUserId;
    @Column(name="add_time")
    private Date addTime;

    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Integer getOpType() {
        return opType;
    }
    public void setOpType(Integer opType) {
        this.opType = opType;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getOrganizationId() {
        return organizationId;
    }
    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}