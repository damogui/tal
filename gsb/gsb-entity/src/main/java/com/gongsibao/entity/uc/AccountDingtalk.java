package com.gongsibao.entity.uc;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_account_dingtalk",header="钉钉帐号关联表")
public class AccountDingtalk extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -5946061479514587599L;
	@Column(name="account_id")
    private Integer accountId;
	
	@Reference(foreignKey="accountId",header="合作帐号")
	private Account account;
	
	 @Column(name="openid",header="openid")
    private String openid;
    
    @Column(name="unionid",header="unionid")
    private String unionid;
    
    @Column(name="dingid",header="dingid")
    private String dingid;
    
    @Column(name="nick",header="昵称")
    private String nick;
    
    @Column(name="register_time",header="注册时间")
    private Date registerTime;
    
    @Column(name="head_url",header="头像")
    private String headUrl;

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

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getDingid() {
		return dingid;
	}

	public void setDingid(String dingid) {
		this.dingid = dingid;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
}