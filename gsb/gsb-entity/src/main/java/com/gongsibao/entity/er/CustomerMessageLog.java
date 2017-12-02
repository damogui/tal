package com.gongsibao.entity.er;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="er_customer_message_log")
public class CustomerMessageLog extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 7268314220975775045L;
	@Column(name="er_customer_id",header="ErCustomerId")
    private Integer erCustomerId;
    @Column(name="er_customer_message_id",header="ErCustomerMessageId")
    private Integer erCustomerMessageId;
    @Column(name="status",header="status")
    private Integer status;
    @Column(name="add_time",header="AddTime")
    private Date addTime;

    public Integer getErCustomerId() {
        return erCustomerId;
    }
    public void setErCustomerId(Integer erCustomerId) {
        this.erCustomerId = erCustomerId;
    }
    public Integer getErCustomerMessageId() {
        return erCustomerMessageId;
    }
    public void setErCustomerMessageId(Integer erCustomerMessageId) {
        this.erCustomerMessageId = erCustomerMessageId;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}