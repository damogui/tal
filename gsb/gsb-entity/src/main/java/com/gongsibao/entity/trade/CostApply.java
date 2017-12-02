package com.gongsibao.entity.trade;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_cost_apply",header="请款申请表")
public class CostApply extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 279739555189523764L;
	@Column(name="payee_name",header="付款金额")
    private Integer amount;

    @Column(name="invoice_time",header="收发票日期")
    private Date invoiceTime;
    
    @Column(name="payee_name",header="收款单位名称")
    private String payeeName;
    
    @Column(name="payee_bank",header="收款单位开户行")
    private String payeeBank;
    
    @Column(name="payee_bank_no",header="收款单位银行账号")
    private String payeeBankNo;
    
    @Column(name="status",header="0待审核, 1通过, 2驳回")
    private Integer status;
    
    @Column(name="remark",header="成本备注")
    private String remark;
    
    @Column(name="audit_user",header="审核人")
    private Integer auditUser;
    
    @Column(name="audit_remark",header="审核意见")
    private String auditRemark;
    
    @Column(name="add_user",header="添加人")
    private Integer addUser;

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

}