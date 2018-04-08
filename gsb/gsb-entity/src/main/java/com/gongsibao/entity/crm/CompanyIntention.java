package com.gongsibao.entity.crm;


import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.crm.dic.CapitalType;
import com.gongsibao.entity.crm.dic.CompanyOrgType;
import com.gongsibao.entity.crm.dic.CompanyType;
import com.gongsibao.entity.crm.dic.RegisterCapitalType;
import com.gongsibao.entity.crm.dic.Taxpayer;

@Table(name="crm_company_intention",header="企业信息")
public class CompanyIntention extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 5151405593241349093L;
	
	@Column(name="name",header="字号(公司名称)，简称")
	private String name;
	
    @Column(name="org_type",header="公司组织形式(441) 44101有限公司、44102有限合伙公司、44103股份有限公司、44104分公司、44105集团公司、44106个体户、44107中外合资公司、44108外商独资公司、44109外资代表处")
    private CompanyOrgType orgType;

    @Column(name="optional_name",header="备选字号")
    private String optionalName;
    
    @Column(name="full_name",header="完整公司名称")
    private String fullName;
    
    @Column(name="company_name",header="工商审核批准的公司名称")
    private String companyName;
    
    @Column(name="company_type",header="公司分类 0地域+公司名+行业特征+公司类型, 1公司名+（地域）+行业特征+公司类型 2公司名+行业特征+（地域）+公司类型")
    private CompanyType companyType;
    
    @Column(name="code",header="统一社会信用代码")
    private String code;
    
    @Column(name="order_prod_id",header="产品订单ID")
    private Integer orderProdId;
    
    @Column(name="order_contact_name",header="订单联系人姓名")
    private String orderContactName;
    
    @Column(name="order_contact_mobile",header="订单联系人电话")
    private String orderContactMobile;
    
    @Column(name="order_contact_email",header="订单联系人邮箱")
    private String orderContactEmail;
    
    @Column(name="setup_status",header="设立状态(0.未设立;1.设立)")
    private Boolean setupStatus;
    
    @Column(name="city_id",header="省市区序号")
    private Integer cityId;
    
    @Column(name="is_self_address",header="是否自有地址（1是、2否(收费地址)、3否(免费地址)、4否(全部地址)）")
    private Integer isSelfAddress;
    
    @Column(name="address",header="地址")
    private String address;
    
    @Column(name="capital_type",header="公司资金类型（1内资，2外资）")
    private CapitalType capitalType;
    
    @Column(name="register_capital",header="注册资金")
    private Integer registerCapital;
    
    @Column(name="register_capital_type",header="注册资本类型 0 人民币(万) 1 美元(万)")
    private RegisterCapitalType registerCapitalType;
    
    @Column(name="is_self_capital",header="是否自有资金（0否，1是）")
    private Boolean isSelfCapital;
    
    @Column(name="is_express",header="是否快递结果材料")
    private Boolean isExpress;
    
    @Column(name="is_name_verify",header="是否已核名")
    private Boolean isNameVerify;
    
    @Column(name="name_verify_file_id",header="核名材料文件ID")
    private Integer nameVerifyFileId;
    
    @Column(name="verify_no",header="核准号")
    private String verifyNo;
    
    @Column(name="business_type",header="经营类型 字典type 431")
    private Integer businessTypeId;
    
    @Reference(foreignKey="businessTypeId",header="经营类型 ")
    private Dict businessType;
    
    @Column(name="owned_business_type",header="自定义经营类型")
    private String ownedBusinessType;
    
    @Column(name="business_scope_supply",header="经营范围补充")
    private String businessScopeSupply;

    @Column(name="has_directorate",header="是否设立董事会")
    private Boolean hasDirectorate;
    
    @Column(name="street",header="公司注册地所属街道办事处")
    private String street;
    
    @Column(name="police_station",header="公司注册地所属派出所")
    private String policeStation;
    
    @Column(name="is_delete",header="是否删除")
    private Boolean isDelete;
    
    @Column(name="area",header="地区")
    private String area;
    
    @Column(name="organization_no",header="组织机构代码证")
    private String organizationNo;
    
    @Column(name="legal_person",header="法人")
    private String legalPerson;
    
    @Column(name="set_up_date",header="成立日期")
    private Date setUpDate;
    
    @Column(name="paid_years",header="实缴年限")
    private String paidYears;
    
    @Column(name="operating_life",header="经营年限")
    private String operatingLife;
    
    @Column(name="telephone",header="固定电话")
    private String telephone;

    @Column(name="order_contract_qq",header="订单联系qq")
    private String orderContractQq;
    
    @Column(name="order_contract_wechat",header="订单联系微信")
    private String orderContractWechat;
    
    @Column(name="residence_type",header="住所情况 1自有 2公司宝提供 3租房")
    private Integer residenceType;
    
    @Column(name="house_owner",header="房屋产权人")
    private String houseOwner;
    
    @Column(name="house_space",header="房屋面积")
    private Integer houseSpace;
    
    @Column(name="logo_url",header="logo")
    private String logoUrl;
    
    @Column(name="nation_tax",header="国税")
    private String nationTax;
    
    @Column(name="local_tax",header="地税")
    private String localTax;
    
    @Column(name="is_famous",header="是否知名企业")
    private Boolean isFamous = false;
    
    @Column(name="is_group",header="是否集团企业")
    private Boolean isGroup = false;
    
    @Column(name="remark",header="说明")
    private String remark;

    @Column(name="description",header="说明(不知道和上面有什么区别)")
    private String description;
    
    @Column(name="finish_time",header="一证三章领取时间")
    private Date finishTime;
    
    @Column(name="is_bbk",header="是否是八百客的数据")
    private String isBbk="0";
    
    @Column(name="taxpayer",header="1，一般纳税人  2，小规模纳税人")
    private Taxpayer taxpayer;
    
    @Column(name="certificate_term_validity",header="一证通有效期")
    private String certificateTermValidity;
    
    @Column(name="national_tax_office",header="所属国税所")
    private String nationalTaxOffice;
    
    @Column(name="national_tax_collector",header="国税专管员")
    private String nationalTaxCollector;
    
    @Column(name="national_tax_collector_mobile",header="国税专管员电话")
    private String nationalTaxCollectorMobile;
    
    @Column(name="local_tax_office",header="所属地税所")
    private String localTaxOffice;
    
    @Column(name="local_tax_collector",header="地税专管员")
    private String localTaxCollector;
    
    @Column(name="local_tax_collector_mobile",header="地税专管员电话")
    private String localTaxCollectorMobile;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CompanyOrgType getOrgType() {
		return orgType;
	}

	public void setOrgType(CompanyOrgType orgType) {
		this.orgType = orgType;
	}

	public String getOptionalName() {
		return optionalName;
	}

	public void setOptionalName(String optionalName) {
		this.optionalName = optionalName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getOrderProdId() {
		return orderProdId;
	}

	public void setOrderProdId(Integer orderProdId) {
		this.orderProdId = orderProdId;
	}

	public String getOrderContactName() {
		return orderContactName;
	}

	public void setOrderContactName(String orderContactName) {
		this.orderContactName = orderContactName;
	}

	public String getOrderContactMobile() {
		return orderContactMobile;
	}

	public void setOrderContactMobile(String orderContactMobile) {
		this.orderContactMobile = orderContactMobile;
	}

	public String getOrderContactEmail() {
		return orderContactEmail;
	}

	public void setOrderContactEmail(String orderContactEmail) {
		this.orderContactEmail = orderContactEmail;
	}

	public Boolean getSetupStatus() {
		return setupStatus;
	}

	public void setSetupStatus(Boolean setupStatus) {
		this.setupStatus = setupStatus;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getIsSelfAddress() {
		return isSelfAddress;
	}

	public void setIsSelfAddress(Integer isSelfAddress) {
		this.isSelfAddress = isSelfAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public CapitalType getCapitalType() {
		return capitalType;
	}

	public void setCapitalType(CapitalType capitalType) {
		this.capitalType = capitalType;
	}

	public RegisterCapitalType getRegisterCapitalType() {
		return registerCapitalType;
	}

	public void setRegisterCapitalType(RegisterCapitalType registerCapitalType) {
		this.registerCapitalType = registerCapitalType;
	}

	public Integer getRegisterCapital() {
		return registerCapital;
	}

	public void setRegisterCapital(Integer registerCapital) {
		this.registerCapital = registerCapital;
	}


	public Boolean getIsSelfCapital() {
		return isSelfCapital;
	}

	public void setIsSelfCapital(Boolean isSelfCapital) {
		this.isSelfCapital = isSelfCapital;
	}

	public Boolean getIsExpress() {
		return isExpress;
	}

	public void setIsExpress(Boolean isExpress) {
		this.isExpress = isExpress;
	}

	public Boolean getIsNameVerify() {
		return isNameVerify;
	}

	public void setIsNameVerify(Boolean isNameVerify) {
		this.isNameVerify = isNameVerify;
	}

	public Integer getNameVerifyFileId() {
		return nameVerifyFileId;
	}

	public void setNameVerifyFileId(Integer nameVerifyFileId) {
		this.nameVerifyFileId = nameVerifyFileId;
	}

	public String getVerifyNo() {
		return verifyNo;
	}

	public void setVerifyNo(String verifyNo) {
		this.verifyNo = verifyNo;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public Dict getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Dict businessType) {
		this.businessType = businessType;
	}

	public String getOwnedBusinessType() {
		return ownedBusinessType;
	}

	public void setOwnedBusinessType(String ownedBusinessType) {
		this.ownedBusinessType = ownedBusinessType;
	}

	public String getBusinessScopeSupply() {
		return businessScopeSupply;
	}

	public void setBusinessScopeSupply(String businessScopeSupply) {
		this.businessScopeSupply = businessScopeSupply;
	}

	public Boolean getHasDirectorate() {
		return hasDirectorate;
	}

	public void setHasDirectorate(Boolean hasDirectorate) {
		this.hasDirectorate = hasDirectorate;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPoliceStation() {
		return policeStation;
	}

	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOrganizationNo() {
		return organizationNo;
	}

	public void setOrganizationNo(String organizationNo) {
		this.organizationNo = organizationNo;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public Date getSetUpDate() {
		return setUpDate;
	}

	public void setSetUpDate(Date setUpDate) {
		this.setUpDate = setUpDate;
	}

	public String getPaidYears() {
		return paidYears;
	}

	public void setPaidYears(String paidYears) {
		this.paidYears = paidYears;
	}

	public String getOperatingLife() {
		return operatingLife;
	}

	public void setOperatingLife(String operatingLife) {
		this.operatingLife = operatingLife;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getOrderContractQq() {
		return orderContractQq;
	}

	public void setOrderContractQq(String orderContractQq) {
		this.orderContractQq = orderContractQq;
	}

	public String getOrderContractWechat() {
		return orderContractWechat;
	}

	public void setOrderContractWechat(String orderContractWechat) {
		this.orderContractWechat = orderContractWechat;
	}

	public Integer getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(Integer residenceType) {
		this.residenceType = residenceType;
	}

	public String getHouseOwner() {
		return houseOwner;
	}

	public void setHouseOwner(String houseOwner) {
		this.houseOwner = houseOwner;
	}

	public Integer getHouseSpace() {
		return houseSpace;
	}

	public void setHouseSpace(Integer houseSpace) {
		this.houseSpace = houseSpace;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getNationTax() {
		return nationTax;
	}

	public void setNationTax(String nationTax) {
		this.nationTax = nationTax;
	}

	public String getLocalTax() {
		return localTax;
	}

	public void setLocalTax(String localTax) {
		this.localTax = localTax;
	}

	public Boolean getIsFamous() {
		return isFamous;
	}

	public void setIsFamous(Boolean isFamous) {
		this.isFamous = isFamous;
	}

	public Boolean getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(Boolean isGroup) {
		this.isGroup = isGroup;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getIsBbk() {
		return isBbk;
	}

	public void setIsBbk(String isBbk) {
		this.isBbk = isBbk;
	}

	public Taxpayer getTaxpayer() {
		return taxpayer;
	}

	public void setTaxpayer(Taxpayer taxpayer) {
		this.taxpayer = taxpayer;
	}

	public String getCertificateTermValidity() {
		return certificateTermValidity;
	}

	public void setCertificateTermValidity(String certificateTermValidity) {
		this.certificateTermValidity = certificateTermValidity;
	}

	public String getNationalTaxOffice() {
		return nationalTaxOffice;
	}

	public void setNationalTaxOffice(String nationalTaxOffice) {
		this.nationalTaxOffice = nationalTaxOffice;
	}

	public String getNationalTaxCollector() {
		return nationalTaxCollector;
	}

	public void setNationalTaxCollector(String nationalTaxCollector) {
		this.nationalTaxCollector = nationalTaxCollector;
	}

	public String getNationalTaxCollectorMobile() {
		return nationalTaxCollectorMobile;
	}

	public void setNationalTaxCollectorMobile(String nationalTaxCollectorMobile) {
		this.nationalTaxCollectorMobile = nationalTaxCollectorMobile;
	}

	public String getLocalTaxOffice() {
		return localTaxOffice;
	}

	public void setLocalTaxOffice(String localTaxOffice) {
		this.localTaxOffice = localTaxOffice;
	}

	public String getLocalTaxCollector() {
		return localTaxCollector;
	}

	public void setLocalTaxCollector(String localTaxCollector) {
		this.localTaxCollector = localTaxCollector;
	}

	public String getLocalTaxCollectorMobile() {
		return localTaxCollectorMobile;
	}

	public void setLocalTaxCollectorMobile(String localTaxCollectorMobile) {
		this.localTaxCollectorMobile = localTaxCollectorMobile;
	}
}