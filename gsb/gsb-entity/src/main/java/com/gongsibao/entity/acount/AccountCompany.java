package com.gongsibao.entity.acount;
import com.gongsibao.entity.BaseEntity;
import org.netsharp.core.annotations.*;
import org.netsharp.entity.Entity;
import java.util.Date;

@Table(name="uc_account_company")
public class AccountCompany extends BaseEntity {
    private static final long serialVersionUID = -1L;


    @Column(name="account_id",header="会员id")
    private int accountId;
    @Column(name="crm_company_id",header="crm_customer主键id")
    private int crmCompanyId;
    @Column(name="yj_company_id",header="yj_company_id")
    private int yjCompanyId;
    @Column(name="taurus_company_id",header="泰尔companyid")
    private String taurusCompanyId;
    @Column(name="company_name",header="冗余企业名称")
    private String companyName;
    @Column(name="legal_person",header="法人")
    private String legalPerson; //法人
    @Column(name="mobile",header="联系电话")
    private String mobile;

    @Column(name="industry_id",header="主营行业id")
    private int industryId;

    @Exclusive
    private String industryName;

    @Column(name="order_prod_id",header="明细订单id")
    private int orderProdId;

    @Exclusive
    private String orderProdIdStr;
    @Column(name="in_use",header="是否在使用")
    private int inUse;
    @Column(name="status",header="审核状态 0审核中 1审核通过 2审核驳回 3注册中 4注册完成 9 删除")
    private int status;
    @Column(name="auditor_id",header="审核人")
    private int auditorId;
    @Column(name="audit_time",header="审核时间")
    private Date auditTime;
    @Column(name="reject_reason",header="驳回原因")
    private String rejectReason;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCrmCompanyId() {
        return crmCompanyId;
    }

    public void setCrmCompanyId(int crmCompanyId) {
        this.crmCompanyId = crmCompanyId;
    }

    public int getYjCompanyId() {
        return yjCompanyId;
    }

    public void setYjCompanyId(int yjCompanyId) {
        this.yjCompanyId = yjCompanyId;
    }

    public String getTaurusCompanyId() {
        return taurusCompanyId;
    }

    public void setTaurusCompanyId(String taurusCompanyId) {
        this.taurusCompanyId = taurusCompanyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getIndustryId() {
        return industryId;
    }

    public void setIndustryId(int industryId) {
        this.industryId = industryId;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public int getOrderProdId() {
        return orderProdId;
    }

    public void setOrderProdId(int orderProdId) {
        this.orderProdId = orderProdId;
    }

    public String getOrderProdIdStr() {
        return orderProdIdStr;
    }

    public void setOrderProdIdStr(String orderProdIdStr) {
        this.orderProdIdStr = orderProdIdStr;
    }

    public int getInUse() {
        return inUse;
    }

    public void setInUse(int inUse) {
        this.inUse = inUse;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(int auditorId) {
        this.auditorId = auditorId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

}
