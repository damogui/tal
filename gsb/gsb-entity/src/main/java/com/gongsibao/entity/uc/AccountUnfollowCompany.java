package com.gongsibao.entity.uc;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.yj.Company;

@Table(name="uc_account_unfollow_company",header="用户取消关注企业表")
public class AccountUnfollowCompany extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 6799905202142736661L;
	@Column(name="account_id",header="会员id")
    private Integer accountId;
	
	@Reference(foreignKey="accountId",header="合作帐号")
	private Account account;
	
    @Column(name="company_id",header="企业id")
    private Integer companyId;
    
	@Reference(foreignKey="companyId",header="云聚企业")
	private Company company;

    public Integer getAccountId() {
        return accountId;
    }
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
}
