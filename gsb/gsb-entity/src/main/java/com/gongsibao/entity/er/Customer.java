package com.gongsibao.entity.er;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="er_customer")
public class Customer extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -676112419944274107L;
	@Column(name="",header="name")
    private String name;
    @Column(name="",header="mobile")
    private String mobile;
    @Column(name="add_user_id",header="AddUserId")
    private Integer addUserId;
    @Column(name="add_time",header="AddTime")
    private Date addTime;
    @Column(name="",header="status")
    private Integer status;
    @Column(name="tenant_id",header="TenantId")
    private Integer tenantId;
    @Column(name="crm_id",header="CrmId")
    private Integer crmId;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
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
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getTenantId() {
        return tenantId;
    }
    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
    public Integer getCrmId() {
        return crmId;
    }
    public void setCrmId(Integer crmId) {
        this.crmId = crmId;
    }
}