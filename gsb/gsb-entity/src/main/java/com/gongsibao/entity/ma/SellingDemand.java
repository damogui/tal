package com.gongsibao.entity.ma;

import java.util.Date;
import java.util.List;

import org.netsharp.core.annotations.BizCode;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.pcc.entity.ProvinceCityCounty;

import com.gongsibao.entity.ma.dic.AddressMode;
import com.gongsibao.entity.ma.dic.DemandState;
import com.gongsibao.entity.ma.dic.IndustryFeature;
import com.gongsibao.entity.ma.dic.PriceInterval;
import com.gongsibao.entity.ma.dic.SelingStatus;
import com.gongsibao.entity.ma.dic.SellingDemandTaxMode;
@BizCode(bizType="SD")
@Table(name="ma_selling_demand",header="出售需求")
public class SellingDemand extends Demand {

	private static final long serialVersionUID = -6409886054356852794L;
	
    @Column(name = "state",header="需求单状态")
    private DemandState state = DemandState.UNSUBMIT;

	@Column(name="company_name",size=200,header="公司名称")
	private String companyName;
	
	//--------------------
	// 尽调信息
	//--------------------
	@Column(name="business_license",size=500,header="营业执照")
	private String  businessLicense;

	@Column(name="regist_date",header="成立日期(开始)")
	private Date registDate;
	
	@Column(name="regist_date_end",header="成立日期(结束)")
	private Date registDateEnd;
	
	@Column(name="has_branch_company",header="有分公司")
	private Boolean hasBranchCompany = false;
	
	@Column(name="has_subsidiary_company",header="有子公司")
	private Boolean hasSubsidiaryCompany = false;
	
	@Column(name="has_year_report",header="有年报")
	private Boolean hasYearReport = false;
	
	@Column(name="address_mode",header="地址")
	private AddressMode addressMode;

	@Column(name="profitable",header="企业盈利")
	private Boolean profitable = false;
	
	@Column(name="company_feature",header="公司特点")
	private IndustryFeature  companyFeature;
	
	@Column(name="province_id")
	private Integer provinceId;
	
	@Reference(foreignKey="provinceId",header="省份")
	private ProvinceCityCounty province;
	
	@Column(name="city_id")
	private Integer cityId;
	
	@Reference(foreignKey="cityId",header="城市")
	private ProvinceCityCounty city;
	
	@Column(name="county_id")
	private Integer countyId;
	
	@Reference(foreignKey="countyId",header="区/县")
	private ProvinceCityCounty county;
	
	@Column(name="shareholder_count",header="股东人数")
	private Integer shareholderCount;

	@Column(name="tax_mode",header="纳税人")
	private SellingDemandTaxMode taxMode;
	
	@Subs(foreignKey="sellingDemandId",header="子公司",subType=SellingDemandSubsidiaryCompany.class)
	private List<SellingDemandSubsidiaryCompany> subdiaryieCompanyDetails;
	
	@Subs(foreignKey="sellingDemandId",header="分公司",subType=SellingDemandBranchCompany.class)
	private List<SellingDemandBranchCompany> brancheCompanyDetails;
	
	@Subs(foreignKey="sellingDemandId",header="流水明细",subType=SellingDemandTurnoverDetail.class)
	private List<SellingDemandTurnoverDetail> turnoverDetails;
	
	
	//--------------------
	// 交易信息
	//--------------------
	
	
	@Column(name="full_name",header="企业具体名称")
	private String fullName;
	
	@Column(name="license_advantage",header="执照优势")
	private String licenseAdvantage;
	
    @Column(name = "seling_status",header="状态")
    private SelingStatus selingStatus = SelingStatus.UNSOLD;

	@Column(name="price_interval",header="售价区间")
	private PriceInterval priceInterval;
	
	
	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getLicenseAdvantage() {
		return licenseAdvantage;
	}


	public void setLicenseAdvantage(String licenseAdvantage) {
		this.licenseAdvantage = licenseAdvantage;
	}


	public PriceInterval getPriceInterval() {
		return priceInterval;
	}


	public void setPriceInterval(PriceInterval priceInterval) {
		this.priceInterval = priceInterval;
	}


	public DemandState getState() {
		return state;
	}


	public void setState(DemandState state) {
		this.state = state;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getBusinessLicense() {
		return businessLicense;
	}


	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}


	public Date getRegistDate() {
		return registDate;
	}


	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}


	public Boolean getHasBranchCompany() {
		return hasBranchCompany;
	}


	public void setHasBranchCompany(Boolean hasBranchCompany) {
		this.hasBranchCompany = hasBranchCompany;
	}


	public Boolean getHasSubsidiaryCompany() {
		return hasSubsidiaryCompany;
	}


	public void setHasSubsidiaryCompany(Boolean hasSubsidiaryCompany) {
		this.hasSubsidiaryCompany = hasSubsidiaryCompany;
	}


	public Boolean getHasYearReport() {
		return hasYearReport;
	}


	public void setHasYearReport(Boolean hasYearReport) {
		this.hasYearReport = hasYearReport;
	}


	public AddressMode getAddressMode() {
		return addressMode;
	}


	public void setAddressMode(AddressMode addressMode) {
		this.addressMode = addressMode;
	}


	public Boolean getProfitable() {
		return profitable;
	}


	public void setProfitable(Boolean profitable) {
		this.profitable = profitable;
	}


	public Integer getProvinceId() {
		return provinceId;
	}


	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}


	public ProvinceCityCounty getProvince() {
		return province;
	}


	public void setProvince(ProvinceCityCounty province) {
		this.province = province;
	}


	public Integer getCityId() {
		return cityId;
	}


	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}


	public ProvinceCityCounty getCity() {
		return city;
	}


	public void setCity(ProvinceCityCounty city) {
		this.city = city;
	}


	public Integer getCountyId() {
		return countyId;
	}


	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}


	public ProvinceCityCounty getCounty() {
		return county;
	}


	public void setCounty(ProvinceCityCounty county) {
		this.county = county;
	}


	public Integer getShareholderCount() {
		return shareholderCount;
	}


	public void setShareholderCount(Integer shareholderCount) {
		this.shareholderCount = shareholderCount;
	}


	public List<SellingDemandSubsidiaryCompany> getSubdiaryieCompanyDetails() {
		return subdiaryieCompanyDetails;
	}


	public void setSubdiaryieCompanyDetails(
			List<SellingDemandSubsidiaryCompany> subdiaryieCompanyDetails) {
		this.subdiaryieCompanyDetails = subdiaryieCompanyDetails;
	}


	public List<SellingDemandBranchCompany> getBrancheCompanyDetails() {
		return brancheCompanyDetails;
	}


	public void setBrancheCompanyDetails(
			List<SellingDemandBranchCompany> brancheCompanyDetails) {
		this.brancheCompanyDetails = brancheCompanyDetails;
	}


	public List<SellingDemandTurnoverDetail> getTurnoverDetails() {
		return turnoverDetails;
	}


	public void setTurnoverDetails(List<SellingDemandTurnoverDetail> turnoverDetails) {
		this.turnoverDetails = turnoverDetails;
	}


	public SelingStatus getSelingStatus() {
		return selingStatus;
	}


	public void setSelingStatus(SelingStatus selingStatus) {
		this.selingStatus = selingStatus;
	}


	public IndustryFeature getCompanyFeature() {
		return companyFeature;
	}


	public void setCompanyFeature(IndustryFeature companyFeature) {
		this.companyFeature = companyFeature;
	}


	public SellingDemandTaxMode getTaxMode() {
		return taxMode;
	}


	public void setTaxMode(SellingDemandTaxMode taxMode) {
		this.taxMode = taxMode;
	}


	public Date getRegistDateEnd() {
		return registDateEnd;
	}


	public void setRegistDateEnd(Date registDateEnd) {
		this.registDateEnd = registDateEnd;
	}
	
	
}
