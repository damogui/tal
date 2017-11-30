package com.gongsibao.entity.er;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="er_order_task")
public class OrderTask extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6161050199907350221L;
	@Column(header="name")
    private String name;
    @Column(name="workflow_id",header="WorkflowId")
    private Integer workflowId;
    @Column(name="order_id",header="OrderId")
    private Integer orderId;
    @Column(name="city_id",header="CityId")
    private Integer cityId;
    @Column(name="role_id",header="RoleId")
    private Integer roleId;
    @Column(header="property")
    private Integer property;
    @Column(header="days")
    private Integer days;
    @Column(header="cost")
    private Integer cost;
    @Column(name="begin_time",header="BeginTime")
    private Date beginTime;
    @Column(name="end_time",header="EndTime")
    private Date endTime;
    @Column(name="sub_time",header="SubTime")
    private Date subTime;
    @Column(name="start_time",header="StartTime")
    private Date startTime;
    @Column(name="complete_time",header="CompleteTime")
    private Date completeTime;
    @Column(header="place")
    private String place;
    @Column(header="status")
    private Integer status;
    @Column(name="is_complain",header="IsComplain")
    private Integer isComplain;
    @Column(header="complain")
    private String complain;
    @Column(name="is_high_risk",header="IsHighRisk")
    private Integer isHighRisk;
    @Column(name="is_enabled",header="IsEnabled")
    private Integer isEnabled;
    @Column(name="is_current",header="IsCurrent")
    private Integer isCurrent;
    @Column(header="sort")
    private Integer sort;
    @Column(name="is_overtime",header="IsOvertime")
    private Integer isOvertime;
    @Column(name="follow_user_id",header="FollowUserId")
    private Integer followUserId;
    @Column(name="add_user_id",header="AddUserId")
    private Integer addUserId;
    @Column(name="add_time",header="AddTime")
    private Date addTime;
    @Column(name="tenant_id",header="TenantId")
    private Integer tenantId;
    @Column(header="frequency")
    private Integer frequency;
    @Column(name="remind_days",header="RemindDays")
    private Integer remindDays;
    @Column(name="timing_frequency",header="TimingFrequency")
    private Integer timingFrequency;
    @Column(name="timing_time",header="TimingTime")
    private String timingTime;
    @Column(name="is_exception",header="IsException")
    private Integer isException;

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
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getCityId() {
        return cityId;
    }
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
    public Integer getCost() {
        return cost;
    }
    public void setCost(Integer cost) {
        this.cost = cost;
    }
    public Date getBeginTime() {
        return beginTime;
    }
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public Date getSubTime() {
        return subTime;
    }
    public void setSubTime(Date subTime) {
        this.subTime = subTime;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getCompleteTime() {
        return completeTime;
    }
    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getIsComplain() {
        return isComplain;
    }
    public void setIsComplain(Integer isComplain) {
        this.isComplain = isComplain;
    }
    public String getComplain() {
        return complain;
    }
    public void setComplain(String complain) {
        this.complain = complain;
    }
    public Integer getIsHighRisk() {
        return isHighRisk;
    }
    public void setIsHighRisk(Integer isHighRisk) {
        this.isHighRisk = isHighRisk;
    }
    public Integer getIsEnabled() {
        return isEnabled;
    }
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }
    public Integer getIsCurrent() {
        return isCurrent;
    }
    public void setIsCurrent(Integer isCurrent) {
        this.isCurrent = isCurrent;
    }
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public Integer getIsOvertime() {
        return isOvertime;
    }
    public void setIsOvertime(Integer isOvertime) {
        this.isOvertime = isOvertime;
    }
    public Integer getFollowUserId() {
        return followUserId;
    }
    public void setFollowUserId(Integer followUserId) {
        this.followUserId = followUserId;
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
    public Integer getIsException() {
        return isException;
    }
    public void setIsException(Integer isException) {
        this.isException = isException;
    }
}