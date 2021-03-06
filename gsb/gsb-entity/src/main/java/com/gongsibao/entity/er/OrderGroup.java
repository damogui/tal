package com.gongsibao.entity.er;


import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="er_order_group")
public class OrderGroup extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 3098995847740545994L;
	@Column(name="name",header="name")
    private String name;
    @Column(name="is_enabled",header="IsEnabled")
    private Integer isEnabled;
    @Column(name="add_time",header="AddTime")
    private Date addTime;
    @Column(name="add_user_id",header="AddUserId")
    private Integer addUserId;
    @Column(name="tenant_id",header="TenantId")
    private Integer tenantId;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getIsEnabled() {
        return isEnabled;
    }
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }
    public Integer getTenantId() {
        return tenantId;
    }
    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}