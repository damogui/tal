package com.gongsibao.entity.cw;

import java.util.ArrayList;
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
*  报销单实体  
* 项目名称：gsb-entity   
* 类名称：Expense   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月20日 下午2:53:30   
* @version
 */

@Table(name="cw_expense",header="财务报销单")
public class Expense extends BizEntity{
	
	/**  
	* @Fields field:field:{todo}  
	*/ 
	private static final long serialVersionUID = 5429422354310479780L;

	
	@Column(name="amount",header="报销金额")
	private Integer amount;
	
	@Column(name="loan_amount",header="借款金额")
	private Integer loanAmount;
	
	
	@Column(name="total_amount",header="报销总金额")
	private Integer totalAmount;
	
	@Column(name="is_offset",header="是否冲抵借款")
	private Boolean isOffset;
	
	@Column(name="type",header="报销类型  1：市场费报销，2：招待费报销，3：差旅费报销")
	private FinanceDict.ExpenseBillType  type = FinanceDict.ExpenseBillType.ExpenseType_1;
	
	
	@Column(name = "set_of_books_id", header = "付款单位（对应套帐）")
	private Integer setOfBooksId;
	
	@Reference(foreignKey = "setOfBooksId")
	private SetOfBooks setOfBooks;
	
	@Column(name = "payment_method", header = "付款方式 1:现金 ，2：转账 ，3：支票")
	private FinanceDict.PaymentMethod paymentMethod = FinanceDict.PaymentMethod.XJ;
	
	@Column(name = "entertain_company", header = "招待公司名称")
	private String entertainCompany;
	
	@Column(name = "entertain_customer", header = "招待客户姓名")
	private String entertainCustomer;
	
	
	@Column(name = "entertain_date", header = "招待日期")
	private String entertainDate;
	
	@Column(name = "entertain_place", header = "招待地点")
	private String entertainPlace;
	
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
	
	
	@Subs(subType = File.class, foreignKey = "formId", header = "报销附件")
	private List<File> files = new ArrayList<>();

	@Subs(subType = AuditRecord.class, foreignKey = "formId", header = "审核明细")
	private List<AuditRecord> auditItem =  new ArrayList<AuditRecord>();
	
	

	@Subs(subType = TripRecord.class, foreignKey = "expenseId", header = "行程明细")
	private List<TripRecord> tripItem =  new ArrayList<TripRecord>();
	
	@Subs(subType = SubsidyRecord.class, foreignKey = "expenseId", header = "补助明细")
	private List<SubsidyRecord> subsidyItem =  new ArrayList<SubsidyRecord>();
	
	
	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public FinanceDict.ExpenseBillType getType() {
		return type;
	}

	public void setType(FinanceDict.ExpenseBillType type) {
		this.type = type;
	}

	public Integer getSetOfBooksId() {
		return setOfBooksId;
	}

	public void setSetOfBooksId(Integer setOfBooksId) {
		this.setOfBooksId = setOfBooksId;
	}

	public SetOfBooks getSetOfBooks() {
		return setOfBooks;
	}

	public void setSetOfBooks(SetOfBooks setOfBooks) {
		this.setOfBooks = setOfBooks;
	}

	public FinanceDict.PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(FinanceDict.PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getEntertainCompany() {
		return entertainCompany;
	}

	public void setEntertainCompany(String entertainCompany) {
		this.entertainCompany = entertainCompany;
	}

	public String getEntertainCustomer() {
		return entertainCustomer;
	}

	public void setEntertainCustomer(String entertainCustomer) {
		this.entertainCustomer = entertainCustomer;
	}


	public String getEntertainPlace() {
		return entertainPlace;
	}

	public void setEntertainPlace(String entertainPlace) {
		this.entertainPlace = entertainPlace;
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

	public String getEntertainDate() {
		return entertainDate;
	}

	public void setEntertainDate(String entertainDate) {
		this.entertainDate = entertainDate;
	}

	public List<TripRecord> getTripItem() {
		return tripItem;
	}

	public void setTripItem(List<TripRecord> tripItem) {
		this.tripItem = tripItem;
	}

	public List<SubsidyRecord> getSubsidyItem() {
		return subsidyItem;
	}

	public void setSubsidyItem(List<SubsidyRecord> subsidyItem) {
		this.subsidyItem = subsidyItem;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Integer loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Boolean getIsOffset() {
		return isOffset;
	}

	public void setIsOffset(Boolean isOffset) {
		this.isOffset = isOffset;
	}
	
	
}
