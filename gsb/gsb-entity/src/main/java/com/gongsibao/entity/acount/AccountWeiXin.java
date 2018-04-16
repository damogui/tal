package com.gongsibao.entity.acount;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.crm.dic.Important;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import java.util.Date;

@Table(name="uc_account_wx")
public class AccountWeiXin extends BaseEntity {

	private static final long serialVersionUID = 2827881401134957483L;
	
	@Column(name="nick_name",header="名称")
	private String nickName;

	@Column(name="subscribe",header="1关注0取消")
	private String subscribe;

	@Column(name="open_id",header="openid")
    private String openid;
    
	@Column(name="sex",header="sex")
    private Integer sex;
    
	@Column(name="city",header="广州")
    private String city;
    
    @Column(name="province",header="广东")
    private String province;
    
    @Column(name="country",header="中国")
    private String country;

    @Column(name="head_imgurl",header="头像")
    private String headImgurl ;
    
    @Column(name="subscribe_time",header="关注时间")
    private Date subscribeTime;

	
	@Column(name = "subscribe_scene", header = "关注来源")
	private String subscribeScene;

	@Column(name = "account_id", header = "account表id")
	private String accountId;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadImgurl() {
		return headImgurl;
	}

	public void setHeadImgurl(String headImgurl) {
		this.headImgurl = headImgurl;
	}

	public Date getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getSubscribeScene() {
		return subscribeScene;
	}

	public void setSubscribeScene(String subscribeScene) {
		this.subscribeScene = subscribeScene;
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}
}