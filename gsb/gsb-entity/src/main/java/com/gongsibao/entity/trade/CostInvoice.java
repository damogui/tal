package com.gongsibao.entity.trade;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_cost_invoice",header="请款申请表")
public class CostInvoice extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1210118499769673504L;
	@Column(name="account_name",header="客户名称")
    private String accountName;

    @Column(name="invoice_info",header="发票内容")
    private String invoiceInfo;
    
    @Column(name="amount",header="发票金额")
    private Integer amount;
    
    @Column(name="invoice_time",header="开票日期")
    private Date invoiceTime;
    
    @Column(name="type_id",header="开票类型，type=308")
    private Integer typeId;
    
    @Column(name="status",header="0待审核, 1通过, 2驳回")
    private Integer status;
    
    @Column(name="remark",header="成本备注")
    private String remark;
    
    @Column(name="audit_user",header="审核人")
    private Integer auditUser;
    
    @Column(name="audit_remark",header="审核意见")
    private String auditRemark;
    
    @Column(name="add_user",header="AddUser")
    private Integer addUser;

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

}