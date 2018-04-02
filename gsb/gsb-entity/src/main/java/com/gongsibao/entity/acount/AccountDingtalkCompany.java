package com.gongsibao.entity.acount;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.uc.dic.AuthLevel;
import com.gongsibao.entity.yj.Company;

@Table(name="uc_account_dingtalk_company")
public class AccountDingtalkCompany extends BaseEntity {

	private static final long serialVersionUID = -6027874014286488658L;
	@Column(name="company_id",header="云聚企业id")
    private Integer companyId;
	
	@Reference(foreignKey="companyId",header="云聚企业")
	private Company company;
	
    @Column(name="is_auth",header="企业是否经过钉钉认证")
    private Boolean legal = false;
    
    @Column(name="is_manager",header="当前用户是否为该企业的管理人员")
    private Boolean manager = false;
    
    @Column(name="rights_level",header="该企业的权益等级")
    private Integer rightsLevel = 0;
    
    @Column(name="corp_name",header="企业名称")
    private String corpName;
    
    @Column(name="corp_id",header="企业id")
    private String corpId;
    
    @Column(name="invite_code",header="表示邀请码，只有填写过且是ISV自己邀请码的数据才会返回,否则值为空字符串")
    private String inviteCode;
    
    @Column(name="industry",header="表示企业所属行业")
    private String industry;
    
    @Column(name="license_code",header="序列号")
    private String licenseCode;
    
    @Column(name="auth_channel",header="渠道码")
    private String authChannel;
    
    @Column(name="auth_channel_type",header="渠道类型,为了避免渠道码重复，可与渠道码共同确认渠道（可能为空。非空时当前只有满天星类型，值为STAR_ACTIVITY）")
    private String authChannelType;
    
    @Column(name="permanent_code",header="钉钉企业临时/永久授权码")
    private String permanentCode;
    
    @Column(name="is_authenticated",header="企业是否认证")
    private Boolean authenticated = false;
    
    @Column(name="auth_level",header="企业认证等级，0：未认证，1：高级认证，2：中级认证，3：初级认证")
    private AuthLevel authLevel;
    
    @Column(name="invite_url",header="企业邀请链接")
    private String inviteUrl;
    
    @Column(name="agent",header="授权的应用信息")
    private String agent;
    
    @Column(name="corpLogoUrl",header="企业logo")
    private String corpLogoUrl;
    
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Boolean getLegal() {
		return legal;
	}

	public void setLegal(Boolean legal) {
		this.legal = legal;
	}

	public Boolean getManager() {
		return manager;
	}

	public void setManager(Boolean manager) {
		this.manager = manager;
	}

	public Integer getRightsLevel() {
		return rightsLevel;
	}

	public void setRightsLevel(Integer rightsLevel) {
		this.rightsLevel = rightsLevel;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getLicenseCode() {
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	public String getAuthChannel() {
		return authChannel;
	}

	public void setAuthChannel(String authChannel) {
		this.authChannel = authChannel;
	}

	public String getAuthChannelType() {
		return authChannelType;
	}

	public void setAuthChannelType(String authChannelType) {
		this.authChannelType = authChannelType;
	}

	public String getPermanentCode() {
		return permanentCode;
	}

	public void setPermanentCode(String permanentCode) {
		this.permanentCode = permanentCode;
	}

	public Boolean getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}

	public AuthLevel getAuthLevel() {
		return authLevel;
	}

	public void setAuthLevel(AuthLevel authLevel) {
		this.authLevel = authLevel;
	}

	public String getInviteUrl() {
		return inviteUrl;
	}

	public void setInviteUrl(String inviteUrl) {
		this.inviteUrl = inviteUrl;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getCorpLogoUrl() {
		return corpLogoUrl;
	}

	public void setCorpLogoUrl(String corpLogoUrl) {
		this.corpLogoUrl = corpLogoUrl;
	}
}