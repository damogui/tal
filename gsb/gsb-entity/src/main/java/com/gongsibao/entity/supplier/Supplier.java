package com.gongsibao.entity.supplier;

import java.util.Date;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.entity.Employee;

import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.crm.dic.NotifiedType;
import com.gongsibao.entity.crm.dic.Sex;
import com.gongsibao.entity.crm.dic.TaskCustomerType;
import com.gongsibao.entity.supplier.dict.BankAccountType;
import com.gongsibao.entity.supplier.dict.ResponseTime;
import com.gongsibao.entity.supplier.dict.SupplierStatus;
import com.gongsibao.entity.supplier.dict.SupplierType;

@Table(name = "sp_supplier", header = "服务商")
public class Supplier extends Entity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 9055417222309634407L;

	@Column(name = "status", header = "状态")
	private SupplierStatus status = SupplierStatus.NOTOPEN;

	// 1、基本信息:

	// 公司:北京快无忧财务咨询有限公司
	@Column(name = "name", header = "服务商名称")
	private String name;

	// 姓名:王桂峰
	@Column(name = "contact", header = "联系人")
	private String contact;

	// 手机号:15011499738
	@Column(name = "mobile_phone", header = "手机号（开户时要校验手机号是否存在）")
	private String mobilePhone;

	// 性别

	@Column(name = "sex", header = "性别，0保密、1男、2女")
	private Sex sex = Sex.SECRECY;

	// 座机:
	@Column(name = "telephone", header = "座机")
	private String telePhone;

	@Column(name = "post_code", header = "邮编")
	private String postCode;

	@Column(name = "fax", header = "传真")
	private String fax;

	// QQ:971741522
	@Column(name = "qq", header = "QQ")
	private String qq;

	// 微信:
	@Column(name = "weixin", header = "微信")
	private String weixin;

	// 邮箱:971741522@qq.com
	@Column(name = "email", header = "邮箱")
	private String email;

	// 头像:
	@Column(name = "head_portrait", header = "头像")
	private String headPortrait;

	@Column(name = "province_id")
	private Integer provinceId;

	@Reference(foreignKey = "provinceId", header = "省份")
	private Dict province;

	@Column(name = "city_id")
	private Integer cityId;

	@Reference(foreignKey = "cityId", header = "城市")
	private Dict city;

	@Column(name = "county_id")
	private Integer countyId;

	@Reference(foreignKey = "countyId", header = "区/县")
	private Dict county;

	// 所在地:丰台区
	@Column(name = "address", size = 300, header = "地址")
	private String address;

	// 2、认证信息:
	@Column(name = "identity_card", header = "身份证号")
	private String identityCard;

	@Column(name = "identity_card_photo_front", header = "身份证照片正面")
	private String identityCardPhotoFont;

	@Column(name = "identity_card_photo_reverse", header = "身份证照片反面")
	private String identityCardPhotoReverse;

	@Column(name = "start_business", header = "从业开始时间")
	private Date startBusiness;

	@Column(name = "license_photo", header = "执业资质照片")
	private String licensePhoto;

	// 3、服务信息:

	// 服务类型:行政审批财税服务工商服务人事社保

	@Subs(foreignKey = "supplierId", header = "服务产品", subType = SupplierProduct.class)
	private List<SupplierProduct> serviceProducts;

	// 响应时间:5分钟内
	@Column(name = "response_time", header = "响应时间")
	private ResponseTime responseTime;

	// 服务宣言:诚信为本，德行天下
	@Column(name = "service_declaration", header = "服务宣言")
	private String serviceDeclaration;

	// 服务简介:工商注册，代理记账，商标注册，股权转让。
	@Column(name = "service_intro", header = "服务简介")
	private String serviceIntro;

	// 收件地址:北京市丰台区菜户营桥北鹏润家园静苑B座2202。
	@Column(name = "consignee_address", size = 300, header = "收件地址")
	private String consigneeAddress;

	// 4、财务信息:

	// 银行账户类型:单位账户
	
	// 开户行:招商银行北京海淀黄庄支行
	@Column(name = "bank_account_type", header = "帐户类别")
	private BankAccountType bankAccountType = BankAccountType.ENTERPRISE;
	
	// 开户行:招商银行北京海淀黄庄支行
	@Column(name = "bank_name", header = "开户行")
	private String bankName;

	// 银行账号:110919906510802
	@Column(name = "bank_num", header = "开户行号")
	private String bankNum;

	// 银行账户:北京快无忧财务咨询有限公司
	@Column(name = "bank_account_name", header = "开户行帐号")
	private String bankAccountName;

	// 5、系统配置:

	@Column(name = "customer_type", header = "所属分组类别（1：新客户  2：老客户）")
	private TaskCustomerType customerType = TaskCustomerType.All;

	@Column(name = "category_id")
	private Integer categoryId;

	@Reference(foreignKey = "categoryId", header = "服务商分类")
	private SupplierCategory category;

	@Column(name = "open_time", header = "开户时间")
	private Date openTime;

	@Column(name = "admin_id", header = "管理员帐号Id")
	private Integer adminId = 0;

	@Reference(foreignKey = "adminId", header = "管理员帐号")
	private Employee admin;

	@Column(name = "type", header = "类型：1自营，2平台;3不限")
	private SupplierType type = SupplierType.UNLIMITED;

	@Column(name = "customer_max_count", header = "客户池数量")
	private Integer customerMaxCount = 10000;

	@Column(name = "is_push_report", header = "是否推送报表  0否, 1是")
	private Boolean pushReport = true;

	@Column(name = "notified_type", header = "消息通知类型 ")
	private NotifiedType notifiedType = NotifiedType.Wx;

	@Column(name = "is_auto_assign", header = "是否推自动分配  0否, 1是")
	private Boolean autoAssign = true;

	@Column(name = "is_auto_release", header = "是否推自动释放  0否, 1是")
	private Boolean autoRelease = true;

	@Column(name = "no_follow_days", header = "未跟进天数释放")
	private Integer noFollowDays = 7;

	@Column(name = "is_enable_depart", header = "是否启用部门  0否, 1是")
	private Boolean enableDepart = true;

	@Column(name = "depart_level", header = "部门级次")
	private Integer departLevel;

	@Column(name = "department_count", header = "部门数量")
	private Integer departmentCount = 0;

	@Column(name = "salesman_count", header = "员工数量")
	private Integer salesmanCount = 0;

	@Subs(foreignKey = "supplierId", header = "开通模块", subType = SupplierFunctionModule.class)
	private List<SupplierFunctionModule> modules;

	public SupplierStatus getStatus() {
		return status;
	}

	public void setStatus(SupplierStatus status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Dict getProvince() {
		return province;
	}

	public void setProvince(Dict province) {
		this.province = province;
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

	public Integer getCountyId() {
		return countyId;
	}

	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}

	public Dict getCounty() {
		return county;
	}

	public void setCounty(Dict county) {
		this.county = county;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getIdentityCardPhotoFont() {
		return identityCardPhotoFont;
	}

	public void setIdentityCardPhotoFont(String identityCardPhotoFont) {
		this.identityCardPhotoFont = identityCardPhotoFont;
	}

	public String getIdentityCardPhotoReverse() {
		return identityCardPhotoReverse;
	}

	public void setIdentityCardPhotoReverse(String identityCardPhotoReverse) {
		this.identityCardPhotoReverse = identityCardPhotoReverse;
	}

	public Date getStartBusiness() {
		return startBusiness;
	}

	public void setStartBusiness(Date startBusiness) {
		this.startBusiness = startBusiness;
	}

	public String getLicensePhoto() {
		return licensePhoto;
	}

	public void setLicensePhoto(String licensePhoto) {
		this.licensePhoto = licensePhoto;
	}

	public List<SupplierProduct> getServiceProducts() {
		return serviceProducts;
	}

	public void setServiceProducts(List<SupplierProduct> serviceProducts) {
		this.serviceProducts = serviceProducts;
	}

	public ResponseTime getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(ResponseTime responseTime) {
		this.responseTime = responseTime;
	}

	public String getServiceDeclaration() {
		return serviceDeclaration;
	}

	public void setServiceDeclaration(String serviceDeclaration) {
		this.serviceDeclaration = serviceDeclaration;
	}

	public String getServiceIntro() {
		return serviceIntro;
	}

	public void setServiceIntro(String serviceIntro) {
		this.serviceIntro = serviceIntro;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public TaskCustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(TaskCustomerType customerType) {
		this.customerType = customerType;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public SupplierCategory getCategory() {
		return category;
	}

	public void setCategory(SupplierCategory category) {
		this.category = category;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Employee getAdmin() {
		return admin;
	}

	public void setAdmin(Employee admin) {
		this.admin = admin;
	}

	public SupplierType getType() {
		return type;
	}

	public void setType(SupplierType type) {
		this.type = type;
	}

	public Integer getCustomerMaxCount() {
		return customerMaxCount;
	}

	public void setCustomerMaxCount(Integer customerMaxCount) {
		this.customerMaxCount = customerMaxCount;
	}

	public Boolean getPushReport() {
		return pushReport;
	}

	public void setPushReport(Boolean pushReport) {
		this.pushReport = pushReport;
	}

	public NotifiedType getNotifiedType() {
		return notifiedType;
	}

	public void setNotifiedType(NotifiedType notifiedType) {
		this.notifiedType = notifiedType;
	}

	public Boolean getAutoAssign() {
		return autoAssign;
	}

	public void setAutoAssign(Boolean autoAssign) {
		this.autoAssign = autoAssign;
	}

	public Boolean getAutoRelease() {
		return autoRelease;
	}

	public void setAutoRelease(Boolean autoRelease) {
		this.autoRelease = autoRelease;
	}

	public Integer getNoFollowDays() {
		return noFollowDays;
	}

	public void setNoFollowDays(Integer noFollowDays) {
		this.noFollowDays = noFollowDays;
	}

	public Boolean getEnableDepart() {
		return enableDepart;
	}

	public void setEnableDepart(Boolean enableDepart) {
		this.enableDepart = enableDepart;
	}

	public Integer getDepartLevel() {
		return departLevel;
	}

	public void setDepartLevel(Integer departLevel) {
		this.departLevel = departLevel;
	}

	public Integer getDepartmentCount() {
		return departmentCount;
	}

	public void setDepartmentCount(Integer departmentCount) {
		this.departmentCount = departmentCount;
	}

	public Integer getSalesmanCount() {
		return salesmanCount;
	}

	public void setSalesmanCount(Integer salesmanCount) {
		this.salesmanCount = salesmanCount;
	}

	public List<SupplierFunctionModule> getModules() {
		return modules;
	}

	public void setModules(List<SupplierFunctionModule> modules) {
		this.modules = modules;
	}

	public BankAccountType getBankAccountType() {
		return bankAccountType;
	}

	public void setBankAccountType(BankAccountType bankAccountType) {
		this.bankAccountType = bankAccountType;
	}
}
