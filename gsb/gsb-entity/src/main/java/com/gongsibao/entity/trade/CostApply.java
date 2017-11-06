package com.gongsibao.entity.trade;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_cost_apply")
public class CostApply extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 279739555189523764L;
	@Column(header="amount")
    private Integer amount;
    @Column(name="invoice_time",header="InvoiceTime")
    private Date invoiceTime;
    @Column(name="payee_name",header="PayeeName")
    private String payeeName;
    @Column(name="payee_bank",header="PayeeBank")
    private String payeeBank;
    @Column(name="payee_bank_no",header="PayeeBankNo")
    private String payeeBankNo;
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
    public String getPayeeName() {
        return payeeName;
    }
    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }
    public String getPayeeBank() {
        return payeeBank;
    }
    public void setPayeeBank(String payeeBank) {
        this.payeeBank = payeeBank;
    }
    public String getPayeeBankNo() {
        return payeeBankNo;
    }
    public void setPayeeBankNo(String payeeBankNo) {
        this.payeeBankNo = payeeBankNo;
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