package com.gongsibao.rest.dto.user;


import com.gongsibao.rest.web.dto.PkId;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 用户收货地址DTO
 * @date 2018/4/18 16:59
 */
public class AccountDeliverAddressDTO extends PkId {

    /** 会员id */
    private Integer accountId;
    /** 姓名 */
    private String contacts;
    /** 手机号码 */
    private String mobilePhone;
    /** 固定电话 */
    private String telephone;
    /** 城市序号 */
    private Integer cityId;
    private String cityIdStr;
    /** 城市名称 */
    private String cityName;
    /** 街道地址 */
    private String address;
    /** 邮编 */
    private Integer postcode;
    /** 是否默认 */
    private Integer isDefault;
    /** 备注 */
    private String remark;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
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

    public String getCityIdStr() {
        return cityIdStr;
    }

    public void setCityIdStr(String cityIdStr) {
        this.cityIdStr = cityIdStr;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
