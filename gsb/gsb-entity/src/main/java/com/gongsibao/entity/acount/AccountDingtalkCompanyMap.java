package com.gongsibao.entity.acount;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.yj.Company;

@Table(name="uc_account_dingtalk_company_map")
public class AccountDingtalkCompanyMap extends BaseEntity {

	private static final long serialVersionUID = -2069091657920880209L;
	@Column(name="account_dingtalk_id",header="钉钉会员id")
    private Integer accountDingtalkId;
	
    @Column(name="account_dingtalk_company_id",header="钉钉企业表主键id")
    private Integer accountDingtalkCompanyId;
    
    @Column(name="corp_id",header="冗余钉钉系统企业id")
    private String corpId;
    
    @Column(name="corp_name",header="冗余企业名称")
    private String corpName;
    
    @Column(name="ding_user_id",header="钉钉平台企业员工唯一标识ID")
    private String dingUserId;
    
    @Column(name="is_current_login",header="是否是当前登录企业（0：否，1：是）")
    private Boolean currentLogin = false;
    
    @Column(name="company_id",header="云聚企业id")
    private Integer companyId;
    
	@Reference(foreignKey="companyId",header="云聚企业")
	private Company company;
    
    @Column(name="is_admin",header="是否为企业的管理员, 1表示是, 0表示不是")
    private Boolean admined = false;
    
    @Column(name="is_boss",header="是否为企业的老板, 1表示是, 0表示不是（不能通过接口设置,可以通过OA后台设置）")
    private Boolean boss = false;
    
    @Column(name="position",header="职位信息")
    private String position;
    
    

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Integer getAccountDingtalkId() {
		return accountDingtalkId;
	}

	public void setAccountDingtalkId(Integer accountDingtalkId) {
		this.accountDingtalkId = accountDingtalkId;
	}

	public Integer getAccountDingtalkCompanyId() {
		return accountDingtalkCompanyId;
	}

	public void setAccountDingtalkCompanyId(Integer accountDingtalkCompanyId) {
		this.accountDingtalkCompanyId = accountDingtalkCompanyId;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getDingUserId() {
		return dingUserId;
	}

	public void setDingUserId(String dingUserId) {
		this.dingUserId = dingUserId;
	}

	public Boolean getCurrentLogin() {
		return currentLogin;
	}

	public void setCurrentLogin(Boolean currentLogin) {
		this.currentLogin = currentLogin;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Boolean getAdmined() {
		return admined;
	}

	public void setAdmined(Boolean admined) {
		this.admined = admined;
	}

	public Boolean getBoss() {
		return boss;
	}

	public void setBoss(Boolean boss) {
		this.boss = boss;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}