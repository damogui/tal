package com.gongsibao.entity.uc;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_suppler")
public class Suppler extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -7507364888003905638L;
	@Column(name="uc_user_id")
    private Integer ucUserId;
    private String name;
    @Column(name="mobile_phone")
    private String mobilePhone;
    @Column(name="land_line")
    private String landLine;
    private String qq;
    private String weixin;
    private String email;
    private Integer sex;
    @Column(name="company_name")
    private String companyName;
    @Column(name="head_thumb_file_id")
    private Integer headThumbFileId;
    @Column(name="add_time")
    private Date addTime;
    @Column(name="add_user_id")
    private Integer addUserId;
    @Column(name="city_id")
    private Integer cityId;
    @Column(name="city_name")
    private String cityName;
    @Column(name="id_card")
    private String idCard;
    @Column(name="id_card_front_file_id")
    private Integer idCardFrontFileId;
    @Column(name="id_card_back_file_id")
    private Integer idCardBackFileId;
    private String license;
    @Column(name="begin_time")
    private Date beginTime;
    @Column(name="pre_sales")
    private String preSales;
    @Column(name="response_time")
    private String responseTime;
    private String declar;
    private String intro;
    @Column(name="receive_address")
    private String receiveAddress;
    @Column(name="good_field")
    private String goodField;
    @Column(name="service_tag")
    private String serviceTag;
    @Column(name="bank_type")
    private String bankType;
    @Column(name="bank_no")
    private String bankNo;
    @Column(name="bank_address")
    private String bankAddress;
    @Column(name="bank_account")
    private String bankAccount;
    @Column(name="is_enabled")
    private Integer isEnabled;
    @Column(name="last_info_json")
    private String lastInfoJson;

    public Integer getUcUserId() {
        return ucUserId;
    }
    public void setUcUserId(Integer ucUserId) {
        this.ucUserId = ucUserId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMobilePhone() {
        return mobilePhone;
    }
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    public String getLandLine() {
        return landLine;
    }
    public void setLandLine(String landLine) {
        this.landLine = landLine;
    }
    public String getQq() {
        return qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }
    public String getWeixin() {
        return weixin;
    }
    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public Integer getHeadThumbFileId() {
        return headThumbFileId;
    }
    public void setHeadThumbFileId(Integer headThumbFileId) {
        this.headThumbFileId = headThumbFileId;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }
    public Integer getCityId() {
        return cityId;
    }
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getIdCard() {
        return idCard;
    }
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    public Integer getIdCardFrontFileId() {
        return idCardFrontFileId;
    }
    public void setIdCardFrontFileId(Integer idCardFrontFileId) {
        this.idCardFrontFileId = idCardFrontFileId;
    }
    public Integer getIdCardBackFileId() {
        return idCardBackFileId;
    }
    public void setIdCardBackFileId(Integer idCardBackFileId) {
        this.idCardBackFileId = idCardBackFileId;
    }
    public String getLicense() {
        return license;
    }
    public void setLicense(String license) {
        this.license = license;
    }
    public Date getBeginTime() {
        return beginTime;
    }
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
    public String getPreSales() {
        return preSales;
    }
    public void setPreSales(String preSales) {
        this.preSales = preSales;
    }
    public String getResponseTime() {
        return responseTime;
    }
    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }
    public String getDeclar() {
        return declar;
    }
    public void setDeclar(String declar) {
        this.declar = declar;
    }
    public String getIntro() {
        return intro;
    }
    public void setIntro(String intro) {
        this.intro = intro;
    }
    public String getReceiveAddress() {
        return receiveAddress;
    }
    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }
    public String getGoodField() {
        return goodField;
    }
    public void setGoodField(String goodField) {
        this.goodField = goodField;
    }
    public String getServiceTag() {
        return serviceTag;
    }
    public void setServiceTag(String serviceTag) {
        this.serviceTag = serviceTag;
    }
    public String getBankType() {
        return bankType;
    }
    public void setBankType(String bankType) {
        this.bankType = bankType;
    }
    public String getBankNo() {
        return bankNo;
    }
    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }
    public String getBankAddress() {
        return bankAddress;
    }
    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }
    public String getBankAccount() {
        return bankAccount;
    }
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
    public Integer getIsEnabled() {
        return isEnabled;
    }
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }
    public String getLastInfoJson() {
        return lastInfoJson;
    }
    public void setLastInfoJson(String lastInfoJson) {
        this.lastInfoJson = lastInfoJson;
    }
}