package com.gongsibao.entity.crm;

import java.util.Date;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.crm.dic.AllocationType;
import com.gongsibao.entity.crm.dic.ConsultWay;
import com.gongsibao.entity.crm.dic.FollowStatus;
import com.gongsibao.entity.crm.dic.Important;
import com.gongsibao.entity.crm.dic.Sex;
import com.gongsibao.entity.uc.Organization;

@Table(name="crm_customer",header="客户信息")
public class Customer extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 55983222524461776L;
	@Column(name="account_id",header="帐号Id")
    private Integer accountId=0;
	
    @Column(name="real_name",header="姓名")
    private String realName;
    
    @Column(name="sex",header="性别，0保密、1男、2女")
    private Sex sex = Sex.SECRECY;
    
    @Column(name="mobile",header="手机号码")
    private String mobile;
    
    @Column(name="telephone",header="座机")
    private String telephone;
    
    @Column(name="email",header="邮箱")
    private String email;
    
    @Column(name="qq",header="QQ")
    private String qq;
    
    @Column(name="weixin",header="微信")
    private String weixin;
    
    @Column(name="birdthday",header="客户生日")
    private Date birdthday;
    
    @Column(name="addr",header="客户地址")
    private String addr;
    
    @Column(name="city_id",header="城市id")
    private Integer cityId=0;
    
    @Reference(foreignKey="cityId",header="城市")
    private Dict city;
    
	@Column(name="f_province_id")
	private Integer fProvinceId;
	
	@Reference(foreignKey="fProvinceId",header="省份")
	private Dict fProvince;
	
	@Column(name="f_city_id")
	private Integer fCityId;
	
	@Reference(foreignKey="fCityId",header="城市")
	private Dict fCity;
	
	@Column(name="f_county_id")
	private Integer fCountyId;
	
	@Reference(foreignKey="fCountyId",header="区/县")
	private Dict fCounty;
    
    @Column(name="follow_user_id",header="跟进人Id")
    private Integer followUserId = 0;
    
    @Column(name="follow_status",header="401 CRM客户跟进状态: 4011未跟进、 4012初步跟进、 4013意向签单、 4014已签单、 4015无效客户、4016流失客户、4020潜在客户")
    private FollowStatus followStatus = FollowStatus.FOLLOW_STATUS_1;
    
    @Column(name="unvalid_remark",header="沟通无效原因")
    private String unvalidRemark;
    
    @Column(name="maybe_remark",header="潜在原因")
    private String maybeRemark;
    
    @Column(name="last_follow_time",header="最近跟进时间")
    private Date lastFollowTime;
    
    @Column(name="is_share",header="是否共享 0否 1是")
    private Integer shared = 0;
    
    @Column(name="back_num",header="退回次数")
    private Integer backNum = 0;
    
    @Column(name="back_user_id",header="上一次退回人")
    private Integer backUserId = 0;
    
//    @Column(name="customer_source",header="411 CRM客户来源: 4111 58同城推广、 4112百度广告投放、 4113地推活动、 4114老客户介绍、 4115 企业信息关联、 4116 渠道商、 4117 外呼拓客")
//    private CustomerSource customerSource = CustomerSource.CUSTOMER_SOURCE_0;
    
    @Column(name="customer_source",header="客户来源")
    private Integer customerSourceId = 0;
    
    @Reference(foreignKey="customerSourceId",header="CRM客户来源")
    private Dict customerSource;
    
    @Column(name="customer_source_other",header="客户来源选择其他时填写的详情")
    private String customerSourceOther;
    
    @Column(name="introducer_user_id",header="介绍人id(内部人员)")
    private Integer introducerUserId = 0;
    
    @Column(name="consult_way",header="421 CRM咨询途径: 4211 400电话、 4212 在线客服、 4213企业QQ、 4214 PC官网、 4215 H5官网、 4216 手机APP")
    private ConsultWay consultWay;
    
    @Column(name="consult_way_other",header="咨询途径选择其他时填写的详情")
    private String consultWayOther;
    
    @Column(name="important",header="402 重要程度: 4021普通、 4022中级、 4023高级、 4024VIP")
    private Important important = Important.COMMON;
    
    @Column(name="is_introducer",header="是否客户介绍 0否 1是")
    private Integer introducer = 0;
    
    @Column(name="is_invalid",header="是否无效")
    private Integer invalid = 0;
    
    @Column(name="introducer_id",header="介绍人id")
    private Integer introducerId = 0;
    
    @Column(name="remark",header="备注信息")
    private String remark;
    
    @Column(name="is_bbk",header="是否是八百客的数据")
    private String bbk = "0";
    
    @Column(name="assign_org_id",header="分配部门Id")
    private Integer allocationOrgId = -1;
    
    @Reference(foreignKey="allocationOrgId",header="分配部门")
    private Organization allocationOrg;
    
    @Exclusive
    @Column(name="allocation_type",header="分配方式")
    private AllocationType allocationType = AllocationType.NATURAL;
    
    @Column(name="sms_remark",header="短信备注")
    private String smsRemark;
    
    @Column(name="swt_customer_id",header="商务通客Id")
    private String swtCustomerId;
    
    @Column(name="swt_service_id",header="商务通客服Id")
    private String swtServiceId;
    
	@Subs(foreignKey="customerId",header="意向产品",subType=CustomerProdMap.class)
	private List<CustomerProdMap> prodDetails;
	
    
	@Subs(foreignKey="customerId",header="关联企业",subType=CustomerCompanyMap.class)
	private List<CustomerCompanyMap> companys;
	
    
//	@Subs(foreignKey="customerId",header="客户名片",subType=CustomerProdMap.class)
//	private List<CustomerProdMap> prodDetails;
	
    
//	@Subs(foreignKey="accountId",header="下单记录",subType=SoOrder.class)
//	private List<SoOrder> orders;
	
    
	@Subs(foreignKey="customerId",header="沟通日志",subType=CustomerFollow.class)
	private List<CustomerFollow> follows;


	public Integer getAccountId() {
		return accountId;
	}

	public List<CustomerCompanyMap> getCompanys() {
		return companys;
	}

	public void setCompanys(List<CustomerCompanyMap> companys) {
		this.companys = companys;
	}

	public List<CustomerFollow> getFollows() {
		return follows;
	}

	public void setFollows(List<CustomerFollow> follows) {
		this.follows = follows;
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

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getFollowUserId() {
		return followUserId;
	}

	public void setFollowUserId(Integer followUserId) {
		this.followUserId = followUserId;
	}

	public FollowStatus getFollowStatus() {
		return followStatus;
	}

	public void setFollowStatus(FollowStatus followStatus) {
		this.followStatus = followStatus;
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

	public Date getLastFollowTime() {
		return lastFollowTime;
	}

	public void setLastFollowTime(Date lastFollowTime) {
		this.lastFollowTime = lastFollowTime;
	}

	public Integer getShared() {
		return shared;
	}

	public void setShared(Integer shared) {
		this.shared = shared;
	}

	public Integer getBackNum() {
		return backNum;
	}

	public void setBackNum(Integer backNum) {
		this.backNum = backNum;
	}

	public Integer getBackUserId() {
		return backUserId;
	}

	public void setBackUserId(Integer backUserId) {
		this.backUserId = backUserId;
	}

	public Integer getCustomerSourceId() {
		return customerSourceId;
	}

	public void setCustomerSourceId(Integer customerSourceId) {
		this.customerSourceId = customerSourceId;
	}

	public Dict getCustomerSource() {
		return customerSource;
	}

	public void setCustomerSource(Dict customerSource) {
		this.customerSource = customerSource;
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

	public Integer getIntroducer() {
		return introducer;
	}

	public void setIntroducer(Integer introducer) {
		this.introducer = introducer;
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
	
	public String getBbk() {
		return bbk;
	}

	public void setBbk(String bbk) {
		this.bbk = bbk;
	}
	public Organization getAllocationOrg() {
		return allocationOrg;
	}

	public void setAllocationOrg(Organization allocationOrg) {
		this.allocationOrg = allocationOrg;
	}

	public Integer getAllocationOrgId() {
		return allocationOrgId;
	}

	public void setAllocationOrgId(Integer allocationOrgId) {
		this.allocationOrgId = allocationOrgId;
	}

	public String getSmsRemark() {
		return smsRemark;
	}

	public void setSmsRemark(String smsRemark) {
		this.smsRemark = smsRemark;
	}

	public AllocationType getAllocationType() {
		
		if(this.allocationOrgId != null && this.allocationOrgId.compareTo(0)==1){
			
			return AllocationType.ASSIGN;
		}
		return allocationType;
	}

	public void setAllocationType(AllocationType allocationType) {
		this.allocationType = allocationType;
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

	public Dict getCity() {
		return city;
	}

	public void setCity(Dict city) {
		this.city = city;
	}

	public Integer getfProvinceId() {
		return fProvinceId;
	}

	public void setfProvinceId(Integer fProvinceId) {
		this.fProvinceId = fProvinceId;
	}

	public Dict getfProvince() {
		return fProvince;
	}

	public void setfProvince(Dict fProvince) {
		this.fProvince = fProvince;
	}

	public Integer getfCityId() {
		return fCityId;
	}

	public void setfCityId(Integer fCityId) {
		this.fCityId = fCityId;
	}

	public Dict getfCity() {
		return fCity;
	}

	public void setfCity(Dict fCity) {
		this.fCity = fCity;
	}

	public Integer getfCountyId() {
		return fCountyId;
	}

	public void setfCountyId(Integer fCountyId) {
		this.fCountyId = fCountyId;
	}

	public Dict getfCounty() {
		return fCounty;
	}

	public void setfCounty(Dict fCounty) {
		this.fCounty = fCounty;
	}

	public List<CustomerProdMap> getProdDetails() {
		return prodDetails;
	}

	public void setProdDetails(List<CustomerProdMap> prodDetails) {
		this.prodDetails = prodDetails;
	}

//	public List<SoOrder> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<SoOrder> orders) {
//		this.orders = orders;
//	}
}