package com.gongsibao.entity.cw;

import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

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
	
	@Column(name = "organization_id", header = "费用归属部门（组织机构id）")
	private Integer organizationId;
	
	@Column(name = "set_of_books_id", header = "付款单位（对应套帐）")
	private Integer setOfBooksId;
	
	@Column(name = "collect_invoice_date", header = "收发票日期")
	private Date collectInvoiceDate ;
	
	@Reference(foreignKey = "setOfBooksId")
	private SetOfBooks setOfBooks;
	
	@Column(name = "payment_method", header = "付款方式 1:现金 ，2：转账 ，3：支票")
	private Integer paymentMethod;
	
	@Column(name = "company_account", header = "接收公司帐号")
	private String companyAccount;
	
	@Column(name = "company_bank", header = "接收公司开户行")
	private String companyBank;
	
	@Column(name = "company_name", header = "接收公司名称")
	private String companyName;
	
	@Column(name = "audit_step", header = "审核步骤")
	private Integer auditStep;
	
	@Column(name = "status", header = "状态 1:待审核 ，2：审核中 ，3：已通过")
	private Integer status;

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
