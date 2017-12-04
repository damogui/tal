package com.gongsibao.entity.product;

import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.uc.Organization;
import com.gongsibao.entity.uc.User;

@Table(name="prod_income_settle",header="结算打款申请表")
public class IncomeSettle extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -7053335654604142329L;

	@Column(name="department",header="部门Id")
	private Integer departmentId;
	
	@Reference(foreignKey="departmentId",header="部门")
	private Organization department;
		
    @Column(name="pay_company",header="付款单位")
    private Integer payCompany;
    
    @Column(name="price",header="付款价格")
    private Integer price;
    
    @Column(name="is_invoice",header="有无发票 0无 1有")
    private Boolean invoice = false;
    
    @Column(name="invoice_date",header="发票日期")
    private Date invoiceDate;
    
    @Column(name="pay_company",header="收款单位")
    private String company;
    
    @Column(name="bank",header="收款单位开户行")
    private String bank;
    
    @Column(name="bank_no",header="收款单位银行账号")
    private String bankNo;
    
    @Column(name="remark",header="备注")
    private String remark;

    @Column(name="audit_status",header="审核状态")
    private Integer auditStatus;
    
    @Column(name="audit_user_id",header="审核人")
    private Integer auditUserId;
    
	@Reference(foreignKey="auditUserId",header="审核人")
	private User auditUser;
    
    @Column(name="audit_time",header="审核时间")
    private Date auditTime;

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Organization getDepartment() {
		return department;
	}

	public void setDepartment(Organization department) {
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

	public Boolean getInvoice() {
		return invoice;
	}

	public void setInvoice(Boolean invoice) {
		this.invoice = invoice;
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

	public User getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(User auditUser) {
		this.auditUser = auditUser;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
}