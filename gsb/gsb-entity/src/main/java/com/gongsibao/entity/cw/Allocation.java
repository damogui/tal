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



@Table(name="cw_allocation",header="财务调拨单")
public class Allocation extends BizEntity{

	
	/**  
	* @Fields field:field:{todo}  
	*/ 
	private static final long serialVersionUID = -6864899854578518475L;

	@Column(name = "payer_comapny_id", header = "付款单位（对应套帐）")
	private  Integer  payerCompanyId;
	
	@Reference(foreignKey = "payerCompanyId")
	private SetOfBooks payerCompany;
	
	@Column(name="amount",header="金额")
	private Integer amount = 0 ;
	
	@Column(name = "payee_company_id", header = "收款单位（对应套帐）")
	private Integer payeeCompanyId;
	
	@Reference(foreignKey = "payeeCompanyId")
	private SetOfBooks payeeCompany;
	
	@Column(name = "purpose", header = "用途")
	private FinanceDict.PurposeType purpose = FinanceDict.PurposeType.QT;
	
	
	@Column(name = "company_account", header = "接收公司帐号")
	private String companyAccount;
	
	@Column(name = "company_bank", header = "接收公司开户行")
	private String companyBank;
	
	@Column(name = "audit_step", header = "审核步骤")
	private Integer auditStep;
	
	@Column(name = "status", header = "状态 1:待审核 ，2：审核中 ，3：已通过，4：财务办理")
	private FinanceDict.AuditStatus status = FinanceDict.AuditStatus.Status_1;

	@Column(name = "department_id", header = "创建人所属部门id")
	private Integer departmentId;
	
	@Subs(subType = File.class, foreignKey = "formId", header = "调拨单附件")
    private List<File> files = new ArrayList<>();
	
	
	@Subs(subType = CostDetail.class, foreignKey = "formId", header = "费用明细")
    private List<CostDetail> costDetailItem = new ArrayList<CostDetail>();
    

    @Subs(subType = AuditRecord.class, foreignKey = "formId", header = "审核明细")
    private List<AuditRecord> auditItem =  new ArrayList<AuditRecord>();
	
	public Integer getPayerCompanyId() {
		return payerCompanyId;
	}

	public void setPayerCompanyId(Integer payerCompanyId) {
		this.payerCompanyId = payerCompanyId;
	}

	public SetOfBooks getPayerCompany() {
		return payerCompany;
	}

	public void setPayerCompany(SetOfBooks payerCompany) {
		this.payerCompany = payerCompany;
	}

	public Integer getPayeeCompanyId() {
		return payeeCompanyId;
	}

	public void setPayeeCompanyId(Integer payeeCompanyId) {
		this.payeeCompanyId = payeeCompanyId;
	}

	public SetOfBooks getPayeeCompany() {
		return payeeCompany;
	}

	public void setPayeeCompany(SetOfBooks payeeCompany) {
		this.payeeCompany = payeeCompany;
	}

	public FinanceDict.PurposeType getPurpose() {
		return purpose;
	}

	public void setPurpose(FinanceDict.PurposeType purpose) {
		this.purpose = purpose;
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

	public FinanceDict.AuditStatus getStatus() {
		return status;
	}

	public void setStatus(FinanceDict.AuditStatus status) {
		this.status = status;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public Integer getAuditStep() {
		return auditStep;
	}

	public void setAuditStep(Integer auditStep) {
		this.auditStep = auditStep;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public List<CostDetail> getCostDetailItem() {
		return costDetailItem;
	}

	public void setCostDetailItem(List<CostDetail> costDetailItem) {
		this.costDetailItem = costDetailItem;
	}

	public List<AuditRecord> getAuditItem() {
		return auditItem;
	}

	public void setAuditItem(List<AuditRecord> auditItem) {
		this.auditItem = auditItem;
	}
	
	
	
}
