package com.gongsibao.entity.acount;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name = "uc_account_cooperation", header = "合作公司帐号中间表")
public class AccountCooperation extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "account_id", header = "合作帐号Id")
	private Integer accountId;
	
	@Reference(foreignKey="accountId",header="合作帐号")
	private Account account;
	
	@Column(name = "cooperation_company_id", header = "合作公司Id")
	private Integer cooperationCompanyId;
	
	@Column(name = "branch_id", header = "下属公司ID")
	private Integer branchId;
	
	@Column(name="open_type",header = "开户类型")
	private String openType;
	
	@Column(name = "openid", header = "openid")
	private String openid;
	
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Integer getCooperationCompanyId() {
		return cooperationCompanyId;
	}
	public void setCooperationCompanyId(Integer cooperationCompanyId) {
		this.cooperationCompanyId = cooperationCompanyId;
	}
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	public String getOpenType() {
		return openType;
	}
	public void setOpenType(String openType) {
		this.openType = openType;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	
	

}
