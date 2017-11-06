package com.gongsibao.entity.crm;


import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="crm_company_intention")
public class CompanyIntention extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 5151405593241349093L;
	private String name;
    @Column(name="org_type")
    private Integer orgType;
    @Column(name="optional_name")
    private String optionalName;
    @Column(name="full_name")
    private String fullName;
    @Column(name="company_name")
    private String companyName;
    @Column(name="company_type")
    private Integer companyType;
    private String code;
    @Column(name="order_prod_id")
    private Integer orderProdId;
    @Column(name="order_contact_name")
    private String orderContactName;
    @Column(name="order_contact_mobile")
    private String orderContactMobile;
    @Column(name="order_contact_email")
    private String orderContactEmail;
    @Column(name="setup_status")
    private Integer setupStatus;
    @Column(name="city_id")
    private Integer cityId;
    @Column(name="is_self_address")
    private Integer isSelfAddress;
    private String address;
    @Column(name="capital_type")
    private Integer capitalType;
    @Column(name="register_capital")
    private Integer registerCapital;
    @Column(name="register_capital_type")
    private Integer registerCapitalType;
    @Column(name="is_self_capital")
    private Integer isSelfCapital;
    @Column(name="is_express")
    private Integer isExpress;
    @Column(name="is_name_verify")
    private Integer isNameVerify;
    @Column(name="name_verify_file_id")
    private Integer nameVerifyFileId;
    @Column(name="verify_no")
    private String verifyNo;
    @Column(name="business_type")
    private Integer businessType;
    @Column(name="owned_business_type")
    private String ownedBusinessType;
    @Column(name="business_scope_supply")
    private String businessScopeSupply;
    @Column(name="has_directorate")
    private Integer hasDirectorate;
    private String street;
    @Column(name="police_station")
    private String policeStation;
    @Column(name="is_delete")
    private Integer isDelete;
    private String area;
    @Column(name="organization_no")
    private String organizationNo;
    @Column(name="legal_person")
    private String legalPerson;
    @Column(name="set_up_date")
    private Date setUpDate;
    @Column(name="paid_years")
    private String paidYears;
    @Column(name="operating_life")
    private String operatingLife;
    private String telephone;
    @Column(name="order_contract_qq")
    private String orderContractQq;
    @Column(name="order_contract_wechat")
    private String orderContractWechat;
    @Column(name="residence_type")
    private Integer residenceType;
    @Column(name="house_owner")
    private String houseOwner;
    @Column(name="house_space")
    private Integer houseSpace;
    @Column(name="logo_url")
    private String logoUrl;
    @Column(name="nation_tax")
    private String nationTax;
    @Column(name="local_tax")
    private String localTax;
    @Column(name="is_famous")
    private Integer isFamous;
    @Column(name="is_group")
    private Integer isGroup;
    private String remark;
    private String description;
    @Column(name="finish_time")
    private Date finishTime;
    @Column(name="upd_user_id")
    private Integer updUserId;
    @Column(name="upd_time")
    private Date updTime;
    @Column(name="add_user_id")
    private Integer addUserId;
    @Column(name="add_time")
    private Date addTime;
    @Column(name="is_bbk")
    private String isBbk;
    private Integer taxpayer;
    @Column(name="certificate_term_validity")
    private String certificateTermValidity;
    @Column(name="national_tax_office")
    private String nationalTaxOffice;
    @Column(name="national_tax_collector")
    private String nationalTaxCollector;
    @Column(name="national_tax_collector_mobile")
    private String nationalTaxCollectorMobile;
    @Column(name="local_tax_office")
    private String localTaxOffice;
    @Column(name="local_tax_collector")
    private String localTaxCollector;
    @Column(name="local_tax_collector_mobile")
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
    public Integer getUpdUserId() {
        return updUserId;
    }
    public void setUpdUserId(Integer updUserId) {
        this.updUserId = updUserId;
    }
    public Date getUpdTime() {
        return updTime;
    }
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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