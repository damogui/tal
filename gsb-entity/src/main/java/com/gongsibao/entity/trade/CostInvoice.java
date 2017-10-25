package com.gongsibao.entity.trade;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_cost_invoice")
public class CostInvoice extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1210118499769673504L;
	@Column(name="account_name",header="AccountName")
    private String accountName;
    @Column(name="invoice_info",header="InvoiceInfo")
    private String invoiceInfo;
    @Column(header="amount")
    private Integer amount;
    @Column(name="invoice_time",header="InvoiceTime")
    private Date invoiceTime;
    @Column(name="type_id",header="TypeId")
    private Integer typeId;
    @Column(header="status")
    private Integer status;
    @Column(header="remark")
    private String remark;
    @Column(name="audit_user",header="AuditUser")
    private Integer auditUser;
    @Column(name="audit_remark",header="AuditRemark")
    private String auditRemark;
    @Column(name="add_user",header="AddUser")
    private Integer addUser;
    @Column(name="add_time",header="AddTime")
    private Date addTime;
    @Column(name="upd_time",header="UpdTime")
    private Date updTime;

    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public String getInvoiceInfo() {
        return invoiceInfo;
    }
    public void setInvoiceInfo(String invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public Date getInvoiceTime() {
        return invoiceTime;
    }
    public void setInvoiceTime(Date invoiceTime) {
        this.invoiceTime = invoiceTime;
    }
    public Integer getTypeId() {
        return typeId;
    }
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getAuditUser() {
        return auditUser;
    }
    public void setAuditUser(Integer auditUser) {
        this.auditUser = auditUser;
    }
    public String getAuditRemark() {
        return auditRemark;
    }
    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }
    public Integer getAddUser() {
        return addUser;
    }
    public void setAddUser(Integer addUser) {
        this.addUser = addUser;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public Date getUpdTime() {
        return updTime;
    }
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }
}