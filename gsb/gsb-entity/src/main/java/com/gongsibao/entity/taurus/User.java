package com.gongsibao.entity.taurus;

import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="jnz_user",header="用户信息")
public class User extends BaseEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 2700067655858930985L;

	@Column(name = "mobile", header = "手机号")
	private String mobile;
	
	@Column(name="amount",header="余额")
	private int amount = 0;
	
	@Column(name = "ticket", header = "登录凭证")
	private String ticket;
	
	@Column(name = "remark",size=200, header = "说明")
	private String remark;
	
	@Subs(foreignKey="userId",header="钱包记录",subType=UserWalletLog.class)
	private List<UserWalletLog> walletLogs;
	
	@Subs(foreignKey="userId",header="关注企业",subType=UserCollectCompany.class)
	private List<UserCollectCompany> collectCompanys;
	
	@Subs(foreignKey="accountId",header="舆情关键字",subType=UserDingtalkKeyword.class)
	private List<UserDingtalkKeyword> dingtalkKeywords;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<UserWalletLog> getWalletLogs() {
		return walletLogs;
	}

	public void setWalletLogs(List<UserWalletLog> walletLogs) {
		this.walletLogs = walletLogs;
	}

	public List<UserCollectCompany> getCollectCompanys() {
		return collectCompanys;
	}

	public void setCollectCompanys(List<UserCollectCompany> collectCompanys) {
		this.collectCompanys = collectCompanys;
	}

	public List<UserDingtalkKeyword> getDingtalkKeywords() {
		return dingtalkKeywords;
	}

	public void setDingtalkKeywords(List<UserDingtalkKeyword> dingtalkKeywords) {
		this.dingtalkKeywords = dingtalkKeywords;
	}
	
}
