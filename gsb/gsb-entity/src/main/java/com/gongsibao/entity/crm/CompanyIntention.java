package com.gongsibao.entity.crm;


import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="crm_company_intention",header="")
public class CompanyIntention extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 5151405593241349093L;
	
	@Column(name="name",header="")
	private String name;
	
    @Column(name="org_type",header="")
    private Integer orgType;
    
    @Column(name="optional_name",header="")
    private String optionalName;
    
    @Column(name="full_name",header="")
    private String fullName;
    
    @Column(name="company_name",header="")
    private String companyName;
    
    @Column(name="company_type",header="")
    private Integer companyType;
    
    @Column(name="code",header="")
    private String code;
    
    @Column(name="order_prod_id",header="")
    private Integer orderProdId;
    
    @Column(name="order_contact_name",header="")
    private String orderContactName;
    
    @Column(name="order_contact_mobile",header="")
    private String orderContactMobile;
    
    @Column(name="order_contact_email",header="")
    private String orderContactEmail;
    
    @Column(name="setup_status",header="")
    private Integer setupStatus;
    
    @Column(name="city_id",header="")
    private Integer cityId;
    
    @Column(name="is_self_address",header="")
    private Integer isSelfAddress;
    
    private String address;
    
    @Column(name="capital_type",header="")
    private Integer capitalType;
    
    @Column(name="register_capital",header="")
    private Integer registerCapital;
    
    @Column(name="register_capital_type",header="")
    private Integer registerCapitalType;
    
    @Column(name="is_self_capital",header="")
    private Integer isSelfCapital;
    
    @Column(name="is_express",header="")
    private Integer isExpress;
    
    @Column(name="is_name_verify",header="")
    private Integer isNameVerify;
    
    @Column(name="name_verify_file_id",header="")
    private Integer nameVerifyFileId;
    
    @Column(name="verify_no",header="")
    private String verifyNo;
    
    @Column(name="business_type",header="")
    private Integer businessType;
    
    @Column(name="owned_business_type",header="")
    private String ownedBusinessType;
    
    @Column(name="business_scope_supply",header="")
    private String businessScopeSupply;
    
    @Column(name="has_directorate",header="")
    private Integer hasDirectorate;
    
    @Column(name="street",header="")
    private String street;
    
    @Column(name="police_station",header="")
    private String policeStation;
    
    @Column(name="is_delete",header="")
    private Integer isDelete;
    
    @Column(name="area",header="")
    private String area;
    
    @Column(name="organization_no",header="")
    private String organizationNo;
    
    @Column(name="legal_person",header="")
    private String legalPerson;
    
    @Column(name="set_up_date",header="")
    private Date setUpDate;
    
    @Column(name="paid_years",header="")
    private String paidYears;
    
    @Column(name="operating_life",header="")
    private String operatingLife;
    
    @Column(name="telephone",header="")
    private String telephone;
    
    @Column(name="order_contract_qq",header="")
    private String orderContractQq;
    
    @Column(name="order_contract_wechat",header="")
    private String orderContractWechat;
    
    @Column(name="residence_type",header="")
    private Integer residenceType;
    
    @Column(name="house_owner",header="")
    private String houseOwner;
    
    @Column(name="house_space",header="")
    private Integer houseSpace;
    
    @Column(name="logo_url",header="")
    private String logoUrl;
    
    @Column(name="nation_tax",header="")
    private String nationTax;
    
    @Column(name="local_tax",header="")
    private String localTax;
    
    @Column(name="is_famous",header="")
    private Integer isFamous;
    
    @Column(name="is_group",header="")
    private Integer isGroup;
    
    @Column(name="remark",header="")
    private String remark;
    
    @Column(name="description",header="")
    private String description;
    
    @Column(name="finish_time",header="")
    private Date finishTime;
    
    @Column(name="is_bbk",header="")
    private String isBbk="0";
    
    @Column(name="taxpayer",header="")
    private Integer taxpayer;
    
    @Column(name="certificate_term_validity",header="")
    private String certificateTermValidity;
    
    @Column(name="national_tax_office",header="")
    private String nationalTaxOffice;
    
    @Column(name="national_tax_collector",header="")
    private String nationalTaxCollector;
    
    @Column(name="national_tax_collector_mobile",header="")
    private String nationalTaxCollectorMobile;
    
    @Column(name="local_tax_office",header="")
    private String localTaxOffice;
    
    @Column(name="local_tax_collector",header="")
    private String localTaxCollector;
    
    @Column(name="local_tax_collector_mobile",header="")
    private String localTaxCollectorMobile;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrgType() {
		return orgType;
	}

	public void setOrgType(Integer orgType) {
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

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
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

	public Integer getSetupStatus() {
		return setupStatus;
	}

	public void setSetupStatus(Integer setupStatus) {
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

	public Integer getCapitalType() {
		return capitalType;
	}

	public void setCapitalType(Integer capitalType) {
		this.capitalType = capitalType;
	}

	public Integer getRegisterCapital() {
		return registerCapital;
	}

	public void setRegisterCapital(Integer registerCapital) {
		this.registerCapital = registerCapital;
	}

	public Integer getRegisterCapitalType() {
		return registerCapitalType;
	}

	public void setRegisterCapitalType(Integer registerCapitalType) {
		this.registerCapitalType = registerCapitalType;
	}

	public Integer getIsSelfCapital() {
		return isSelfCapital;
	}

	public void setIsSelfCapital(Integer isSelfCapital) {
		this.isSelfCapital = isSelfCapital;
	}

	public Integer getIsExpress() {
		return isExpress;
	}

	public void setIsExpress(Integer isExpress) {
		this.isExpress = isExpress;
	}

	public Integer getIsNameVerify() {
		return isNameVerify;
	}

	public void setIsNameVerify(Integer isNameVerify) {
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

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
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

	public Integer getHasDirectorate() {
		return hasDirectorate;
	}

	public void setHasDirectorate(Integer hasDirectorate) {
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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
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

	public Integer getIsFamous() {
		return isFamous;
	}

	public void setIsFamous(Integer isFamous) {
		this.isFamous = isFamous;
	}

	public Integer getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(Integer isGroup) {
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

	public Integer getTaxpayer() {
		return taxpayer;
	}

	public void setTaxpayer(Integer taxpayer) {
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