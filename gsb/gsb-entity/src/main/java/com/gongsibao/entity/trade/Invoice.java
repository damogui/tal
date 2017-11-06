package com.gongsibao.entity.trade;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_invoice",header="发票")
public class Invoice extends BaseEntity {
	
	private static final long serialVersionUID = -3543797519223170163L;
	@Column(header="title")
    private String title;
    @Column(name="company_id",header="CompanyId")
    private Integer companyId;
    @Column(name="type_id",header="TypeId")
    private Integer typeId;
    @Column(header="amount")
    private Integer amount;
    @Column(header="content")
    private String content;
    @Column(name="audit_status_id",header="AuditStatusId")
    private Integer auditStatusId;
    @Column(name="receiver_name",header="ReceiverName")
    private String receiverName;
    @Column(name="receiver_mobile_phone",header="ReceiverMobilePhone")
    private String receiverMobilePhone;
    @Column(name="receiver_address",header="ReceiverAddress")
    private String receiverAddress;
    @Column(name="vat_tax_no",header="VatTaxNo")
    private String vatTaxNo;
    @Column(name="vat_address",header="VatAddress")
    private String vatAddress;
    @Column(name="vat_phone",header="VatPhone")
    private String vatPhone;
    @Column(name="vat_bank_name",header="VatBankName")
    private String vatBankName;
    @Column(name="vat_bank_no",header="VatBankNo")
    private String vatBankNo;
    @Column(name="file_id",header="FileId")
    private Integer fileId;
    @Column(name="add_time",header="AddTime")
    private Date addTime;
    @Column(name="add_user_id",header="AddUserId")
    private Integer addUserId;
    @Column(header="remark")
    private String remark;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public Integer getTypeId() {
        return typeId;
    }
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getAuditStatusId() {
        return auditStatusId;
    }
    public void setAuditStatusId(Integer auditStatusId) {
        this.auditStatusId = auditStatusId;
    }
    public String getReceiverName() {
        return receiverName;
    }
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
    public String getReceiverMobilePhone() {
        return receiverMobilePhone;
    }
    public void setReceiverMobilePhone(String receiverMobilePhone) {
        this.receiverMobilePhone = receiverMobilePhone;
    }
    public String getReceiverAddress() {
        return receiverAddress;
    }
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }
    public String getVatTaxNo() {
        return vatTaxNo;
    }
    public void setVatTaxNo(String vatTaxNo) {
        this.vatTaxNo = vatTaxNo;
    }
    public String getVatAddress() {
        return vatAddress;
    }
    public void setVatAddress(String vatAddress) {
        this.vatAddress = vatAddress;
    }
    public String getVatPhone() {
        return vatPhone;
    }
    public void setVatPhone(String vatPhone) {
        this.vatPhone = vatPhone;
    }
    public String getVatBankName() {
        return vatBankName;
    }
    public void setVatBankName(String vatBankName) {
        this.vatBankName = vatBankName;
    }
    public String getVatBankNo() {
        return vatBankNo;
    }
    public void setVatBankNo(String vatBankNo) {
        this.vatBankNo = vatBankNo;
    }
    public Integer getFileId() {
        return fileId;
    }
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
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
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}