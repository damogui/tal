package com.gongsibao.entity.uc;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_account_dingtalk_company")
public class AccountDingtalkCompany extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6027874014286488658L;
	@Column(name="company_id")
    private Integer companyId;
    @Column(name="is_auth")
    private Integer isAuth;
    @Column(name="is_manager")
    private Integer isManager;
    @Column(name="rights_level")
    private Integer rightsLevel;
    @Column(name="corp_name")
    private String corpName;
    @Column(name="corp_id")
    private String corpId;
    @Column(name="invite_code")
    private String inviteCode;
    private String industry;
    @Column(name="license_code")
    private String licenseCode;
    @Column(name="auth_channel")
    private String authChannel;
    @Column(name="auth_channel_type")
    private String authChannelType;
    @Column(name="permanent_code")
    private String permanentCode;
    @Column(name="is_authenticated")
    private Integer isAuthenticated;
    @Column(name="auth_level")
    private Integer authLevel;
    @Column(name="invite_url")
    private String inviteUrl;
    private String agent;
    @Column(name="corp_logo_url")
    private String corpLogoUrl;
    @Column(name="add_time")
    private Date addTime;

    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public Integer getIsAuth() {
        return isAuth;
    }
    public void setIsAuth(Integer isAuth) {
        this.isAuth = isAuth;
    }
    public Integer getIsManager() {
        return isManager;
    }
    public void setIsManager(Integer isManager) {
        this.isManager = isManager;
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
    public Integer getIsAuthenticated() {
        return isAuthenticated;
    }
    public void setIsAuthenticated(Integer isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }
    public Integer getAuthLevel() {
        return authLevel;
    }
    public void setAuthLevel(Integer authLevel) {
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
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}