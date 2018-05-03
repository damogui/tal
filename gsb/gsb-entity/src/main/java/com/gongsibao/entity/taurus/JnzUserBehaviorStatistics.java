package com.gongsibao.entity.taurus;

import java.util.Date;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name = "jnz_user_behavior_statistics", header = "用户行为统计表")
public class JnzUserBehaviorStatistics  extends Persistable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Auto
	@Column(name = "pkid", header = "主键Id")
	private Integer id;
	@Column(name = "mobile", header = "手机号")
	private String mobile;														
	@Column(name = "login_button", header = "点击短信验证或账号密码页的“登录”按钮")
	private String loginButton;											
	@Column(name = "login_success", header = "登录成功")
	private String loginSuccess;											
	@Column(name = "register_button", header = "点击“注册”按钮")
	private String registerButton;										
	@Column(name = "register_success", header = "注册成功")
	private String registerSuccess;										
	@Column(name = "improving_personalData", header = "在注册中完成全部个人资料信息")
	private String improvingPersonalData;					
	@Column(name = "home_page", header = "进入首页")
	private String homePage;												
	@Column(name = "enterprise_list", header = "查询关键词完成，进入企业列表")
	private String enterpriseList;										
	@Column(name = "enterprise_analysis", header = "从企业列表选择要分析的企业")
	private String enterpriseAnalysis;								
	@Column(name = "charging_success", header = "从企业列表选择要分析的企业，并成功扣费")
	private String chargingSuccess;									
	@Column(name = "no_deductions", header = "从企业列表选择要分析的企业，未扣费")
	private String noDeductions;										
	@Column(name = "balance_insufficient_recharge", header = "点击冲值（余额不足充值）")
	private String balanceInsufficientRecharge;			
	@Column(name = "personal_center_recharge", header = "点击充值（个人中心充值）")
	private String personalCenterRecharge;					
	@Column(name = "recharge_success", header = "冲值完成")
	private String rechargeSuccess;									
	@Column(name = "creator", header = "创建人")
	private String creator;

	@Column(name = "add_time", header = "创建时间")
	private Date addTime;														//日期

	@Column(name = "updator", header = "修改人")
	private String updator;

	@Column(name = "upd_time", header = "修改时间")
	private Date updTime;

	@Column(name = "ext1", header = "预留字段1")
	private String ext1;

	@Column(name = "ext2", header = "预留字段2")
	private String ext2;

	@Column(name = "ext3", header = "预留字段3")
	private String ext3;
	
	@Column(name = "ext4", header = "预留字段4")
	private String ext4;
	
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLoginButton() {
		return loginButton;
	}
	public void setLoginButton(String loginButton) {
		this.loginButton = loginButton;
	}
	public String getLoginSuccess() {
		return loginSuccess;
	}
	public void setLoginSuccess(String loginSuccess) {
		this.loginSuccess = loginSuccess;
	}
	public String getRegisterButton() {
		return registerButton;
	}
	public void setRegisterButton(String registerButton) {
		this.registerButton = registerButton;
	}
	public String getRegisterSuccess() {
		return registerSuccess;
	}
	public void setRegisterSuccess(String registerSuccess) {
		this.registerSuccess = registerSuccess;
	}
	public String getImprovingPersonalData() {
		return improvingPersonalData;
	}
	public void setImprovingPersonalData(String improvingPersonalData) {
		this.improvingPersonalData = improvingPersonalData;
	}
	public String getHomePage() {
		return homePage;
	}
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	public String getEnterpriseList() {
		return enterpriseList;
	}
	public void setEnterpriseList(String enterpriseList) {
		this.enterpriseList = enterpriseList;
	}
	public String getEnterpriseAnalysis() {
		return enterpriseAnalysis;
	}
	public void setEnterpriseAnalysis(String enterpriseAnalysis) {
		this.enterpriseAnalysis = enterpriseAnalysis;
	}
	public String getChargingSuccess() {
		return chargingSuccess;
	}
	public void setChargingSuccess(String chargingSuccess) {
		this.chargingSuccess = chargingSuccess;
	}
	public String getNoDeductions() {
		return noDeductions;
	}
	public void setNoDeductions(String noDeductions) {
		this.noDeductions = noDeductions;
	}
	public String getBalanceInsufficientRecharge() {
		return balanceInsufficientRecharge;
	}
	public void setBalanceInsufficientRecharge(String balanceInsufficientRecharge) {
		this.balanceInsufficientRecharge = balanceInsufficientRecharge;
	}
	public String getPersonalCenterRecharge() {
		return personalCenterRecharge;
	}
	public void setPersonalCenterRecharge(String personalCenterRecharge) {
		this.personalCenterRecharge = personalCenterRecharge;
	}
	public String getRechargeSuccess() {
		return rechargeSuccess;
	}
	public void setRechargeSuccess(String rechargeSuccess) {
		this.rechargeSuccess = rechargeSuccess;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public Date getUpdTime() {
		return updTime;
	}
	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public String getExt3() {
		return ext3;
	}
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	public String getExt4() {
		return ext4;
	}
	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}
	
	
	
}
