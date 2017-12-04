package com.gongsibao.entity.uc;

import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.organization.dic.Gender;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_suppler",header="代理商信息")
public class Suppler extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -7507364888003905638L;
	@Column(name="uc_user_id",header="对应帐号信息")
    private Integer ucUserId;
	
    @Reference(foreignKey="ucUserId")
    private User user;
	
	@Column(name="name",header="名称")
    private String name;
    
    @Column(name="mobile_phone",header="手机号")
    private String mobilePhone;
    
    @Column(name="land_line")
    private String landLine;
    
    @Column(name="qq",header="qq")
    private String qq;
    
    @Column(name="weixin",header="微信")
    private String weixin;
    
    @Column(name="email",header="邮箱")
    private String email;

    @Column(name="gender",header="性别")
    private Gender sex;
    
    @Column(name="company_name",header="公司名称")
    private String companyName;
    
    @Column(name="head_thumb_file_id",header="")
    private Integer headThumbFileId;
    
    @Column(name="city_id",header="")
    private Integer cityId;
    
    @Column(name="city_name",header="")
    private String cityName;
    
    @Column(name="id_card",header="身份证号码")
    private String idCard;
    
    @Column(name="id_card_front_file_id",header="")
    private Integer idCardFrontFileId;
    
    @Column(name="id_card_back_file_id",header="")
    private Integer idCardBackFileId;
    
    @Column(name="license",header="许可证")
    private String license;
    
    @Column(name="begin_time",header="开始使用时间")
    private Date beginTime;
    
    @Column(name="pre_sales",header="")
    private String preSales;
    
    @Column(name="response_time",header="响应时间")
    private String responseTime;
    
    @Column(name="declar",header="declar")
    private String declar;
    
    @Column(name="intro",header="介绍")
    private String intro;
    
    @Column(name="receive_address",header="收货地址")
    private String receiveAddress;
    
    @Column(name="good_field",header="擅长领域")
    private String goodField;
    
    @Column(name="service_tag",header="服务标签")
    private String serviceTag;
    
    @Column(name="bank_type",header="银行类型")
    private String bankType;
    
    @Column(name="bank_no",header="银行编号")
    private String bankNo;
    
    @Column(name="bank_address",header="开户行地址")
    private String bankAddress;
    
    @Column(name="bank_account",header="银行账户")
    private String bankAccount;
    
    @Column(name="is_enabled",header="是否启用")
    private Boolean enabled = true;
    
    @Column(name="last_info_json",header="冗余：最后信息json串")
    private String lastInfoJson;

	public Integer getUcUserId() {
		return ucUserId;
	}

	public void setUcUserId(Integer ucUserId) {
		this.ucUserId = ucUserId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Gender getSex() {
		return sex;
	}

	public void setSex(Gender sex) {
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getLastInfoJson() {
		return lastInfoJson;
	}

	public void setLastInfoJson(String lastInfoJson) {
		this.lastInfoJson = lastInfoJson;
	}
}