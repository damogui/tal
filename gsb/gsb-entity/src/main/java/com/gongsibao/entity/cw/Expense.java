package com.gongsibao.entity.cw;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

import com.gongsibao.entity.cw.dict.FinanceDict;

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
	
	@Column(name="reason",header="报销理由")
	private String reason;
	
	@Column(name="type",header="报销类型  1：市场费报销，2：招待费报销，3：差旅费报销")
	private FinanceDict.ExpenseBillType  type = FinanceDict.ExpenseBillType.ExpenseType_1;
	
	@Column(name="form_number",header="单据数量")
	private Integer formNumber;
	
	
	@Column(name = "set_of_books_id", header = "付款单位（对应套帐）")
	private Integer setOfBooksId;
	
	@Column(name = "payment_method", header = "付款方式 1:现金 ，2：转账 ，3：支票")
	private Integer paymentMethod;
	
	
	@Column(name = "company_account", header = "接收公司帐号")
	private String companyccount;
	
	@Column(name = "company_bank", header = "接收公司开户行")
	private String companyank;
	
	@Column(name = "company_name", header = "接收公司名称")
	private String companyame;
	
	@Column(name = "audit_step", header = "审核步骤")
	private Integer auditStep;
	
	@Column(name = "status", header = "状态 1:待审核 ，2：审核中 ，3：已通过")
	private FinanceDict.AuditStatus status = FinanceDict.AuditStatus.Status_1;

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public FinanceDict.ExpenseBillType getType() {
		return type;
	}

	public void setType(FinanceDict.ExpenseBillType type) {
		this.type = type;
	}

	public Integer getFormNumber() {
		return formNumber;
	}

	public void setFormNumber(Integer formNumber) {
		this.formNumber = formNumber;
	}

	public Integer getSetOfBooksId() {
		return setOfBooksId;
	}

	public void setSetOfBooksId(Integer setOfBooksId) {
		this.setOfBooksId = setOfBooksId;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCompanyccount() {
		return companyccount;
	}

	public void setCompanyccount(String companyccount) {
		this.companyccount = companyccount;
	}

	public String getCompanyank() {
		return companyank;
	}

	public void setCompanyank(String companyank) {
		this.companyank = companyank;
	}

	public String getCompanyame() {
		return companyame;
	}

	public void setCompanyame(String companyame) {
		this.companyame = companyame;
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
	
	

}
