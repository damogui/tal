package com.gongsibao.entity.cw;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.attachment.Attachment;
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
*  借款单实体  
* 项目名称：gsb-entity   
* 类名称：Loan   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月20日 下午2:52:39   
* @version
 */

@Table(name="cw_loan",header="财务借款单")
public class Loan extends BizEntity {

	
	/**  
	* @Fields field:field:{todo}  
	*/ 
	private static final long serialVersionUID = 6238108748482170121L;

	
	@Column(name="amount",header="借款金额")
	private Integer amount = 0 ;
	
	@Column(name="repayment_amount",header="还款金额")
	private Integer repaymentAmount = 0 ;
	
	@Column(name="arrears_amount",header="未还金额")
	private Integer arrearsAmount  = 0;
	
	@Column(name="type",header="借款类型  1：日常借款，2：招待费借款，3：差旅费借款")
	private FinanceDict.LoanBillType type = FinanceDict.LoanBillType.LoanType_1;
	
	@Column(name = "set_of_books_id", header = "付款单位（对应套帐）")
	private Integer setOfBooksId;
	
	@Reference(foreignKey = "setOfBooksId")
	private SetOfBooks setOfBooks;
	
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




	public Integer getArrearsAmount() {
		return arrearsAmount;
	}


	public void setArrearsAmount(Integer arrearsAmount) {
		this.arrearsAmount = arrearsAmount;
	}


	public Integer getRepaymentAmount() {
		return repaymentAmount;
	}


	public void setRepaymentAmount(Integer repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}


	public Integer getSetOfBooksId() {
		return setOfBooksId;
	}


	public void setSetOfBooksId(Integer setOfBooksId) {
		this.setOfBooksId = setOfBooksId;
	}


	
	public FinanceDict.LoanBillType getType() {
		return type;
	}


	public void setType(FinanceDict.LoanBillType type) {
		this.type = type;
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

	
	
}

