package com.gongsibao.entity.er;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="er_role")
public class Role extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 3362900848839212024L;
	@Column(name="",header="pid")
    private Integer pid;
    @Column(name="group_id",header="GroupId")
    private Integer groupId;
    @Column(name="",header="name")
    private String name;
    @Column(name="",header="del")
    private Integer del;
    @Column(name="add_time",header="AddTime")
    private Date addTime;
    @Column(name="add_user_id",header="AddUserId")
    private Integer addUserId;
    @Column(name="tenant_id",header="TenantId")
    private Integer tenantId;

    public Integer getPid() {
        return pid;
    }
    public void setPid(Integer pid) {
        this.pid = pid;
    }
    public Integer getGroupId() {
        return groupId;
    }
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getDel() {
        return del;
    }
    public void setDel(Integer del) {
        this.del = del;
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