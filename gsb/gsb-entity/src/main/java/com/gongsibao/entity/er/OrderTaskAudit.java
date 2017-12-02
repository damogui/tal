package com.gongsibao.entity.er;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="er_order_task_audit")
public class OrderTaskAudit extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 6510334822822812487L;
	@Column(name="type_id",header="TypeId")
    private Integer typeId;
    @Column(name="order_task_id",header="OrderTaskId")
    private Integer orderTaskId;
    @Column(name="audit_user_id",header="AuditUserId")
    private Integer auditUserId;
    @Column(name="status",header="status")
    private Integer status;
    @Column(name="ch_old_id",header="ChOldId")
    private Integer chOldId;
    @Column(name="ch_reason",header="ChReason")
    private String chReason;
    @Column(name="ch_enclosure",header="ChEnclosure")
    private String chEnclosure;
    @Column(name="step_old_id",header="StepOldId")
    private Integer stepOldId;
    @Column(name="step_new_id",header="StepNewId")
    private Integer stepNewId;
    @Column(name="step_reason",header="StepReason")
    private String stepReason;
    @Column(name="step_enclosure",header="StepEnclosure")
    private String stepEnclosure;
    @Column(name="reject_reason",header="RejectReason")
    private String rejectReason;
    @Column(name="is_stop",header="IsStop")
    private Integer isStop;
    @Column(name="add_user_id",header="AddUserId")
    private Integer addUserId;
    @Column(name="add_time",header="AddTime")
    private Date addTime;
    @Column(name="stop_time",header="StopTime")
    private Date stopTime;
    @Column(name="end_time",header="EndTime")
    private Date endTime;
    @Column(name="audit_time",header="AuditTime")
    private Date auditTime;
    @Column(name="is_enabled",header="IsEnabled")
    private Integer isEnabled;

    public Integer getTypeId() {
        return typeId;
    }
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    public Integer getOrderTaskId() {
        return orderTaskId;
    }
    public void setOrderTaskId(Integer orderTaskId) {
        this.orderTaskId = orderTaskId;
    }
    public Integer getAuditUserId() {
        return auditUserId;
    }
    public void setAuditUserId(Integer auditUserId) {
        this.auditUserId = auditUserId;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getChOldId() {
        return chOldId;
    }
    public void setChOldId(Integer chOldId) {
        this.chOldId = chOldId;
    }
    public String getChReason() {
        return chReason;
    }
    public void setChReason(String chReason) {
        this.chReason = chReason;
    }
    public String getChEnclosure() {
        return chEnclosure;
    }
    public void setChEnclosure(String chEnclosure) {
        this.chEnclosure = chEnclosure;
    }
    public Integer getStepOldId() {
        return stepOldId;
    }
    public void setStepOldId(Integer stepOldId) {
        this.stepOldId = stepOldId;
    }
    public Integer getStepNewId() {
        return stepNewId;
    }
    public void setStepNewId(Integer stepNewId) {
        this.stepNewId = stepNewId;
    }
    public String getStepReason() {
        return stepReason;
    }
    public void setStepReason(String stepReason) {
        this.stepReason = stepReason;
    }
    public String getStepEnclosure() {
        return stepEnclosure;
    }
    public void setStepEnclosure(String stepEnclosure) {
        this.stepEnclosure = stepEnclosure;
    }
    public String getRejectReason() {
        return rejectReason;
    }
    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
    public Integer getIsStop() {
        return isStop;
    }
    public void setIsStop(Integer isStop) {
        this.isStop = isStop;
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
    public Date getStopTime() {
        return stopTime;
    }
    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public Date getAuditTime() {
        return auditTime;
    }
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
    public Integer getIsEnabled() {
        return isEnabled;
    }
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }
}