package com.gongsibao.entity.crm;

import java.util.Date;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.entity.Employee;

import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.crm.dic.AllocationType;
import com.gongsibao.entity.crm.dic.ConsultWay;
import com.gongsibao.entity.crm.dic.Important;
import com.gongsibao.entity.crm.dic.QualityCategory;
import com.gongsibao.entity.crm.dic.Sex;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.entity.uc.Account;

@Table(name = "n_crm_customer", header = "客户信息")
public class NCustomer extends Entity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -1451778506769623188L;

	@Column(name = "account_id", header = "帐号Id")
	private Integer accountId = 0;
	
	@Reference(foreignKey = "accountId")
	private Account account;

	@Column(name = "real_name", header = "姓名")
	private String realName;

	@Column(name = "sex", header = "性别，0保密、1男、2女")
	private Sex sex = Sex.SECRECY;

	@Column(name = "mobile", header = "手机号码")
	private String mobile;

	@Column(name = "telephone", header = "座机")
	private String telephone;

	@Column(name = "email", header = "邮箱")
	private String email;

	@Column(name = "qq", header = "QQ")
	private String qq;

	@Column(name = "weixin", header = "微信")
	private String weixin;

	@Column(name = "birdthday", header = "客户生日")
	private Date birdthday;

	@Column(name = "addr", header = "客户地址")
	private String addr;

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

	@Column(name = "unvalid_remark", header = "沟通无效原因")
	private String unvalidRemark;

	@Column(name = "maybe_remark", header = "潜在原因")
	private String maybeRemark;

	@Column(name = "customer_source_other", header = "客户来源选择其他时填写的详情")
	private String customerSourceOther;

	@Column(name = "introducer_user_id", header = "介绍人id(内部人员)")
	private Integer introducerUserId = 0;

	@Column(name = "consult_way", header = "421 CRM咨询途径: 4211 400电话、 4212 在线客服、 4213企业QQ、 4214 PC官网、 4215 H5官网、 4216 手机APP")
	private ConsultWay consultWay;

	@Column(name = "consult_way_other", header = "咨询途径选择其他时填写的详情")
	private String consultWayOther;

	@Column(name = "important", header = "402 重要程度: 4021普通、 4022中级、 4023高级、 4024VIP")
	private Important important = Important.COMMON;

	@Column(name = "is_invalid", header = "是否无效")
	private Integer invalid = 0;

	@Column(name = "introducer_id", header = "介绍人id")
	private Integer introducerId = 0;

	@Column(name = "remark", header = "备注信息")
	private String remark;

	@Column(name = "allocation_type", header = "分配方式")
	private AllocationType allocationType = AllocationType.NATURAL;

	@Column(name = "supplier_id", header = "分配服务商Id")
	private Integer supplierId;

	@Reference(foreignKey = "supplierId", header = "分配服务商")
	private Supplier supplier;
	
	@Column(name = "department_id", header = "分配服务商部门Id")
	private Integer departmentId;

	@Reference(foreignKey = "departmentId", header = "分配服务商部门")
	private SupplierDepartment department;

	@Column(name = "sms_remark", header = "短信备注")
	private String smsRemark;

	@Column(name = "swt_customer_id", header = "商务通客Id")
	private String swtCustomerId;

	@Column(name = "swt_service_id", header = "商务通客服Id")
	private String swtServiceId;

	@Column(name = "intention_category", header = "质量分类")
	private QualityCategory intentionCategory;
	
	@Column(name = "quality", header = "质量")
	private NCustomerTaskQuality quality;
	
	@Column(name = "last_follow_time", header = "最近跟进时间")
	private Date lastFollowTime;
	
	@Column(name = "last_foolow_user_id", header = "最后跟进人Id")
	private Integer lastFoolowUserId = 0;
	
	@Reference(foreignKey = "lastFoolowUserId", header = "最后跟进人")
	private Employee lastFoolowUser;
	
	@Column(name = "last_content", size = 1000, header = "最后跟进内容")
	private String lastContent;
	
	@Column(name = "next_foolow_time", header = "下次跟进时间")
	private Date nextFoolowTime;

	@Reference(foreignKey = "customerSourceId", header = "客户来源")
	private Dict customerSource;

	@Column(name = "customer_source_id", header = "客户来源")
	private Integer customerSourceId = 0;

	@Subs(foreignKey = "customerId", header = "客户任务", subType = NCustomerTask.class)
	private List<NCustomerTask> tasks;

	@Subs(foreignKey = "customerId", header = "意向产品", subType = NCustomerProduct.class)
	private List<NCustomerProduct> products;

	@Subs(foreignKey = "customerId", header = "关联企业", subType = NCustomerCompany.class)
	private List<NCustomerCompany> companys;

	@Subs(foreignKey = "customerId", header = "沟通日志", subType = NCustomerTaskFoolow.class)
	private List<NCustomerTaskFoolow> follows;

	@Subs(foreignKey = "customerId", header = "通知日志", subType = NCustomerTaskNotify.class)
	private List<NCustomerTaskNotify> notifys;

	@Subs(foreignKey = "customerId", header = "流转日志", subType = NCustomerChange.class)
	private List<NCustomerChange> changes;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Date getBirdthday() {
		return birdthday;
	}

	public void setBirdthday(Date birdthday) {
		this.birdthday = birdthday;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
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

	public String getUnvalidRemark() {
		return unvalidRemark;
	}

	public void setUnvalidRemark(String unvalidRemark) {
		this.unvalidRemark = unvalidRemark;
	}

	public String getMaybeRemark() {
		return maybeRemark;
	}

	public void setMaybeRemark(String maybeRemark) {
		this.maybeRemark = maybeRemark;
	}

	public String getCustomerSourceOther() {
		return customerSourceOther;
	}

	public void setCustomerSourceOther(String customerSourceOther) {
		this.customerSourceOther = customerSourceOther;
	}

	public Integer getIntroducerUserId() {
		return introducerUserId;
	}

	public void setIntroducerUserId(Integer introducerUserId) {
		this.introducerUserId = introducerUserId;
	}

	public ConsultWay getConsultWay() {
		return consultWay;
	}

	public void setConsultWay(ConsultWay consultWay) {
		this.consultWay = consultWay;
	}

	public String getConsultWayOther() {
		return consultWayOther;
	}

	public void setConsultWayOther(String consultWayOther) {
		this.consultWayOther = consultWayOther;
	}

	public Important getImportant() {
		return important;
	}

	public void setImportant(Important important) {
		this.important = important;
	}

	public Integer getInvalid() {
		return invalid;
	}

	public void setInvalid(Integer invalid) {
		this.invalid = invalid;
	}

	public Integer getIntroducerId() {
		return introducerId;
	}

	public void setIntroducerId(Integer introducerId) {
		this.introducerId = introducerId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public AllocationType getAllocationType() {
		return allocationType;
	}

	public void setAllocationType(AllocationType allocationType) {
		this.allocationType = allocationType;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public SupplierDepartment getDepartment() {
		return department;
	}

	public void setDepartment(SupplierDepartment department) {
		this.department = department;
	}

	public String getSmsRemark() {
		return smsRemark;
	}

	public void setSmsRemark(String smsRemark) {
		this.smsRemark = smsRemark;
	}

	public String getSwtCustomerId() {
		return swtCustomerId;
	}

	public void setSwtCustomerId(String swtCustomerId) {
		this.swtCustomerId = swtCustomerId;
	}

	public String getSwtServiceId() {
		return swtServiceId;
	}

	public void setSwtServiceId(String swtServiceId) {
		this.swtServiceId = swtServiceId;
	}

	public Date getLastFollowTime() {
		return lastFollowTime;
	}

	public void setLastFollowTime(Date lastFollowTime) {
		this.lastFollowTime = lastFollowTime;
	}

	public Dict getCustomerSource() {
		return customerSource;
	}

	public void setCustomerSource(Dict customerSource) {
		this.customerSource = customerSource;
	}

	public Integer getCustomerSourceId() {
		return customerSourceId;
	}

	public void setCustomerSourceId(Integer customerSourceId) {
		this.customerSourceId = customerSourceId;
	}

	public List<NCustomerTask> getTasks() {
		return tasks;
	}

	public void setTasks(List<NCustomerTask> tasks) {
		this.tasks = tasks;
	}

	public List<NCustomerProduct> getProducts() {
		return products;
	}

	public void setProducts(List<NCustomerProduct> products) {
		this.products = products;
	}

	public List<NCustomerCompany> getCompanys() {
		return companys;
	}

	public void setCompanys(List<NCustomerCompany> companys) {
		this.companys = companys;
	}

	public List<NCustomerTaskFoolow> getFollows() {
		return follows;
	}

	public void setFollows(List<NCustomerTaskFoolow> follows) {
		this.follows = follows;
	}

	public List<NCustomerTaskNotify> getNotifys() {
		return notifys;
	}

	public void setNotifys(List<NCustomerTaskNotify> notifys) {
		this.notifys = notifys;
	}

	public List<NCustomerChange> getChanges() {
		return changes;
	}

	public void setChanges(List<NCustomerChange> changes) {
		this.changes = changes;
	}

	public QualityCategory getIntentionCategory() {
		return intentionCategory;
	}

	public void setIntentionCategory(QualityCategory intentionCategory) {
		this.intentionCategory = intentionCategory;
	}

	public NCustomerTaskQuality getQuality() {
		return quality;
	}

	public void setQuality(NCustomerTaskQuality quality) {
		this.quality = quality;
	}

	public Integer getLastFoolowUserId() {
		return lastFoolowUserId;
	}

	public void setLastFoolowUserId(Integer lastFoolowUserId) {
		this.lastFoolowUserId = lastFoolowUserId;
	}

	public Employee getLastFoolowUser() {
		return lastFoolowUser;
	}

	public void setLastFoolowUser(Employee lastFoolowUser) {
		this.lastFoolowUser = lastFoolowUser;
	}

	public String getLastContent() {
		return lastContent;
	}

	public void setLastContent(String lastContent) {
		this.lastContent = lastContent;
	}

	public Date getNextFoolowTime() {
		return nextFoolowTime;
	}

	public void setNextFoolowTime(Date nextFoolowTime) {
		this.nextFoolowTime = nextFoolowTime;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}	
	
}
