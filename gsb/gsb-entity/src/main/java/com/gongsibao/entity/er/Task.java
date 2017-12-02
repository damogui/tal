package com.gongsibao.entity.er;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="er_task")
public class Task extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -5699252859693217962L;
	@Column(name="name",header="name")
    private String name;
    @Column(name="workflow_id",header="WorkflowId")
    private Integer workflowId;
    @Column(name="city_id",header="CityId")
    private Integer cityId;
    @Column(name="property",header="property")
    private Integer property;
    @Column(name="days",header="days")
    private Integer days;
    @Column(name="is_high_risk",header="IsHighRisk")
    private Integer isHighRisk;
    @Column(name="cost",header="cost")
    private Integer cost;
    @Column(name="role_id",header="RoleId")
    private Integer roleId;
    @Column(name="is_template",header="IsTemplate")
    private Integer isTemplate;
    @Column(name="add_user_id",header="AddUserId")
    private Integer addUserId;
    @Column(name="add_time",header="AddTime")
    private Date addTime;
    @Column(name="sort",header="sort")
    private Integer sort;
    @Column(name="tenant_id",header="TenantId")
    private Integer tenantId;
    @Column(name="frequency",header="frequency")
    private Integer frequency;
    @Column(name="remind_days",header="RemindDays")
    private Integer remindDays;
    @Column(name="timing_frequency",header="TimingFrequency")
    private Integer timingFrequency;
    @Column(name="timing_time",header="TimingTime")
    private String timingTime;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getWorkflowId() {
        return workflowId;
    }
    public void setWorkflowId(Integer workflowId) {
        this.workflowId = workflowId;
    }
    public Integer getCityId() {
        return cityId;
    }
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    public Integer getProperty() {
        return property;
    }
    public void setProperty(Integer property) {
        this.property = property;
    }
    public Integer getDays() {
        return days;
    }
    public void setDays(Integer days) {
        this.days = days;
    }
    public Integer getIsHighRisk() {
        return isHighRisk;
    }
    public void setIsHighRisk(Integer isHighRisk) {
        this.isHighRisk = isHighRisk;
    }
    public Integer getCost() {
        return cost;
    }
    public void setCost(Integer cost) {
        this.cost = cost;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public Integer getIsTemplate() {
        return isTemplate;
    }
    public void setIsTemplate(Integer isTemplate) {
        this.isTemplate = isTemplate;
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
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public Integer getTenantId() {
        return tenantId;
    }
    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
    public Integer getFrequency() {
        return frequency;
    }
    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
    public Integer getRemindDays() {
        return remindDays;
    }
    public void setRemindDays(Integer remindDays) {
        this.remindDays = remindDays;
    }
    public Integer getTimingFrequency() {
        return timingFrequency;
    }
    public void setTimingFrequency(Integer timingFrequency) {
        this.timingFrequency = timingFrequency;
    }
    public String getTimingTime() {
        return timingTime;
    }
    public void setTimingTime(String timingTime) {
        this.timingTime = timingTime;
    }
}