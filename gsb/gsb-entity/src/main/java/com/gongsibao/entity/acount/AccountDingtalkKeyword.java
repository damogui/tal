package com.gongsibao.entity.acount;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.yj.Company;

@Table(name="uc_account_dingtalk_keyword")
public class AccountDingtalkKeyword extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 7610680175372603328L;
	@Column(name="account_id",header="官网会员ID")
    private Integer accountId;
	
	@Reference(foreignKey="accountId",header="合作帐号")
	private Account account;
	
	@Column(name="keyword",header="关键字")
    private String keyword;
    
	@Column(name="yj_company_id",header="云聚企业id")
    private Integer companyId;
	
	@Reference(foreignKey="companyId",header="云聚企业")
	private Company company;
	
	@Column(name="status",header="0:正常 1:删除")
    private Boolean deleted = false;
	
	@Column(name="company_name",header="公司名称")
    private String companyName;
	
	@Column(name="user_id",header="会员Id")
    private Integer userId;
	
	@Column(name="type",header="类型：区分金牛座")
    private Integer type;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}