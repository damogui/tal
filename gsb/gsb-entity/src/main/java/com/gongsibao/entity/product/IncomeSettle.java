package com.gongsibao.entity.product;

import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="prod_income_settle")
public class IncomeSettle extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -7053335654604142329L;
	private Integer department;
    @Column(name="pay_company")
    private Integer payCompany;
    private Integer price;
    @Column(name="is_invoice")
    private Integer isInvoice;
    @Column(name="invoice_date")
    private Date invoiceDate;
    private String company;
    private String bank;
    @Column(name="bank_no")
    private String bankNo;
    private String remark;
    @Column(name="add_user_id")
    private Integer addUserId;
    @Column(name="add_time")
    private Date addTime;
    @Column(name="audit_status")
    private Integer auditStatus;
    @Column(name="audit_user_id")
    private Integer auditUserId;
    @Column(name="audit_time")
    private Date auditTime;

    public Integer getDepartment() {
        return department;
    }
    public void setDepartment(Integer department) {
        this.department = department;
    }
    public Integer getPayCompany() {
        return payCompany;
    }
    public void setPayCompany(Integer payCompany) {
        this.payCompany = payCompany;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getIsInvoice() {
        return isInvoice;
    }
    public void setIsInvoice(Integer isInvoice) {
        this.isInvoice = isInvoice;
    }
    public Date getInvoiceDate() {
        return invoiceDate;
    }
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getBank() {
        return bank;
    }
    public void setBank(String bank) {
        this.bank = bank;
    }
    public String getBankNo() {
        return bankNo;
    }
    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
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
    public Integer getAuditStatus() {
        return auditStatus;
    }
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }
    public Integer getAuditUserId() {
        return auditUserId;
    }
    public void setAuditUserId(Integer auditUserId) {
        this.auditUserId = auditUserId;
    }
    public Date getAuditTime() {
        return auditTime;
    }
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
}