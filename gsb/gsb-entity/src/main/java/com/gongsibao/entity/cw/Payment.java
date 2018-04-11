package com.gongsibao.entity.cw;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

import com.gongsibao.entity.bd.File;
import com.gongsibao.entity.cw.dict.FinanceDict;
import com.gongsibao.entity.u8.SetOfBooks;

/**
 * 
*  付款单实体  
* 项目名称：gsb-entity   
* 类名称：Payment   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月20日 下午2:51:35   
* @version
 */

@Table(name="cw_payment",header="财务付款单")
public class Payment extends BizEntity{

	/**  
	* @Fields field:field:{todo}  
	*/ 
	private static final long serialVersionUID = 2341409435968446219L;

	
	@Column(name="amount",header="付款金额")
	private Integer amount;
	
	@Column(name="business_type",header="业务类型 ")
	private FinanceDict.BusinessType businessType = FinanceDict.BusinessType.QT;
	
	
	@Column(name = "set_of_books_id", header = "付款单位（对应套帐）")
	private Integer setOfBooksId;
	
	@Reference(foreignKey = "setOfBooksId")
	private SetOfBooks setOfBooks;
	
	@Column(name = "collect_invoice_date", header = "收发票日期")
	private Date collectInvoiceDate ;
	
	@Column(name = "payment_method", header = "付款方式 1:现金 ，2：转账 ，3：支票")
	private FinanceDict.PaymentMethod paymentMethod = FinanceDict.PaymentMethod.XJ;
	
	@Column(name = "company_account", header = "接收公司帐号")
	private String companyAccount;
	
	@Column(name = "company_bank", header = "接收公司开户行")
	private String companyBank;
	
	@Column(name = "company_name", header = "接收公司名称")
	private String companyName;
	
	@Column(name = "audit_step", header = "审核步骤")
	private Integer auditStep;
	
	@Column(name = "department_id", header = "创建人所属部门id")
	private Integer departmentId;
	
	@Column(name = "status", header = "状态 1:待审核 ，2：审核中 ，3：已通过")
	private FinanceDict.AuditStatus status = FinanceDict.AuditStatus.Status_1;
	
	@Subs(subType = CostDetail.class, foreignKey = "formId", header = "费用明细")
	private List<CostDetail> costDetailItem = new ArrayList<CostDetail>();
	    
	@Subs(subType = File.class, foreignKey = "formId", header = "借款附件")
	private List<File> files = new ArrayList<>();

	@Subs(subType = AuditRecord.class, foreignKey = "formId", header = "审核明细")
	private List<AuditRecord> auditItem =  new ArrayList<AuditRecord>();

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getSetOfBooksId() {
		return setOfBooksId;
	}

	public void setSetOfBooksId(Integer setOfBooksId) {
		this.setOfBooksId = setOfBooksId;
	}

	public FinanceDict.PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(FinanceDict.PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCompanyAccount() {
		return companyAccount;
	}

	public void setCompanyAccount(String companyAccount) {
		this.companyAccount = companyAccount;
	}

	public String getCompanyBank() {
		return companyBank;
	}

	public void setCompanyBank(String companyBank) {
		this.companyBank = companyBank;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getAuditStep() {
		return auditStep;
	}

	public void setAuditStep(Integer auditStep) {
		this.auditStep = auditStep;
	}

	public FinanceDict.AuditStatus getStatus() {
		return status;
	}

	public void setStatus(FinanceDict.AuditStatus status) {
		this.status = status;
	}

	public FinanceDict.BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(FinanceDict.BusinessType businessType) {
		this.businessType = businessType;
	}

	public SetOfBooks getSetOfBooks() {
		return setOfBooks;
	}

	public void setSetOfBooks(SetOfBooks setOfBooks) {
		this.setOfBooks = setOfBooks;
	}

	public Date getCollectInvoiceDate() {
		return collectInvoiceDate;
	}

	public void setCollectInvoiceDate(Date collectInvoiceDate) {
		this.collectInvoiceDate = collectInvoiceDate;
	}

	public List<CostDetail> getCostDetailItem() {
		return costDetailItem;
	}

	public void setCostDetailItem(List<CostDetail> costDetailItem) {
		this.costDetailItem = costDetailItem;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public List<AuditRecord> getAuditItem() {
		return auditItem;
	}

	public void setAuditItem(List<AuditRecord> auditItem) {
		this.auditItem = auditItem;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	
	
}
