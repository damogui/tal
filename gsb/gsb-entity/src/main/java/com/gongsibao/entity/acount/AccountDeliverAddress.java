package com.gongsibao.entity.acount;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;

@Table(name="uc_account_deliver_address",header="帐号交付地址")
public class AccountDeliverAddress extends BaseEntity {
 
	private static final long serialVersionUID = -2295070304488625040L;
	@Column(name="account_id",header="帐号Id")
    private Integer accountId;
	
	@Reference(foreignKey="accountId",header="帐号")
	private Account account;
	
	@Column(name="contacts",header="姓名")
    private String contacts;
    
    @Column(name="mobile_phone",header="手机号")
    private String mobilePhone;
    
    @Column(name="telephone",header="座机号")
    private String telephone;
    
    @Column(name="city_id",header="城市Id")
    private Integer cityId;
    
	@Reference(foreignKey="cityId",header="城市")
	private Dict city;
    
    @Column(name="address",header="详细地址")
    private String address;
    
    @Column(name="postcode",header="邮编")
    private Integer postcode;
    
    @Column(name="is_default",header="默认")
    private Boolean defaulted =false;
    
    @Column(name="remark",header="备注")
    private String remark;

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

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Dict getCity() {
		return city;
	}

	public void setCity(Dict city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPostcode() {
		return postcode;
	}

	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}

	public Boolean getDefaulted() {
		return defaulted;
	}

	public void setDefaulted(Boolean defaulted) {
		this.defaulted = defaulted;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}