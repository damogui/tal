package com.gongsibao.entity.er;


import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="er_workflow")
public class Workflow extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -545492082183418655L;
	@Column(name="name",header="name")
    private String name;
    @Column(name="is_template",header="IsTemplate")
    private Integer isTemplate;
    @Column(name="group_id",header="GroupId")
    private Integer groupId;

    @Column(name="status",header="status")
    private Integer status;
    @Column(name="tenant_id",header="TenantId")
    private Integer tenantId;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getIsTemplate() {
        return isTemplate;
    }
    public void setIsTemplate(Integer isTemplate) {
        this.isTemplate = isTemplate;
    }
    public Integer getGroupId() {
        return groupId;
    }
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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
}