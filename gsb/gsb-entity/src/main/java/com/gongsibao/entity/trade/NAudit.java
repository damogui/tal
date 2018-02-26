package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

/**
 * Created by win on 2018/2/26.
 */
@Table(name = "n_audit", header = "审计")
public class NAudit   extends Entity{
    @Column(name = "applicant_id", header = "申请人Id")
    private  Integer applicantId;
    @Column(name = "audit_status", header = "审计状态")
    private  Integer auditStatus;
    @Column(name = "audit_type", header = "审计类型")
    private  Integer auditType;

    public Integer getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getAuditType() {
        return auditType;
    }

    public void setAuditType(Integer auditType) {
        this.auditType = auditType;
    }
}