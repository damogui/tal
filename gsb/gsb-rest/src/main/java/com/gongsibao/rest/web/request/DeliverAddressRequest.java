package com.gongsibao.rest.web.request;

import java.io.Serializable;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 用户收货地址Request
 * @date 2018/4/19 13:51
 */
public class DeliverAddressRequest implements Serializable{

    private String address;
    private Integer cityId;
    private String contacts;
    private Integer isDefault;
    private String mobilePhone;
    private Integer pkid;
    private Integer accountId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Integer getPkid() {
        return pkid;
    }

    public void setPkid(Integer pkid) {
        this.pkid = pkid;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
