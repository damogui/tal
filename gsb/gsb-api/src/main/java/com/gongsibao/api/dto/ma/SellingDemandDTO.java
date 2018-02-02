package com.gongsibao.api.dto.ma;

import com.gongsibao.entity.ma.DemandFixedAssets;
import com.gongsibao.entity.ma.DemandIntangibleAssets;
import com.gongsibao.entity.ma.DemandQualificationDetail;
import com.gongsibao.entity.ma.dic.*;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.pcc.entity.ProvinceCityCounty;

import java.util.Date;
import java.util.List;

/**
 * Created by win on 2018/2/1.
 */

/*出售需求对外接口实体类*/
public class SellingDemandDTO {


    @Column(name = "id", header = "主键")
    private Integer id;
    @Column(name = "code", header = "编码")
    private String code;
    @Column(name = "company_name", size = 200, header = "公司名称")
    private String companyName;

    @Column(name = "company_type", header = "公司类型")
    private CompanyType companyType;


    @Column(name = "company_feature", header = "公司特点")
    private IndustryFeature companyFeature;
    //公司特点字符串
    private String companyFeatureStr;

    @Column(name = "company_nature", header = "公司性质")
    private CompanyNature companyNature;
    //公司性质字符串
    private String companyNatureStr;

    @Reference(foreignKey = "provinceId", header = "省份")
    private ProvinceCityCounty province;
    //注册省份字符串
    private String provinceStr;

    @Reference(foreignKey = "cityId", header = "城市")
    private ProvinceCityCounty city;

    //注册城市字符串
    private String cityStr;
    @Column(name = "regist_date", header = "成立日期(开始)")
    private Date registDate;

    //成立日期年  2017
    private String registDateStr;


    @Column(name = "tax_mode", header = "纳税人")
    private SellingDemandTaxMode taxMode;
    @Column(name = "address_mode", header = "地址")
    private AddressMode addressMode;



    @Column(name = "regist_date_end", header = "成立日期(结束)")
    private Date registDateEnd;


    @Column(name = "city_id")
    private Integer cityId;



    @Column(name = "county_id")
    private Integer countyId;

    @Reference(foreignKey = "countyId", header = "区/县")
    private ProvinceCityCounty county;
    @Column(name = "has_bank_account", header = "银行账户")
    private Boolean hasBankAccount = false;
    @Column(name = "tax_register", header = "国地税报到")
    private Boolean taxRegister = false;

    @Subs(foreignKey = "demandId", header = "企业资质", subType = DemandQualificationDetail.class)
    private List<DemandQualificationDetail> qualificationDetails;
    @Subs(foreignKey = "demandId", header = "无形资产", subType = DemandIntangibleAssets.class)
    private List<DemandIntangibleAssets> intangibleAssetss;
    @Subs(foreignKey = "demandId", header = "固定资产", subType = DemandFixedAssets.class)
    private List<DemandFixedAssets> fixedAssetss;
    @Column(name = "license_advantage", header = "执照优势")
    private String licenseAdvantage;


    @Column(name = "seling_status", header = "状态")
    private SelingStatus selingStatus = SelingStatus.UNSOLD;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public CompanyNature getCompanyNature() {
        return companyNature;
    }

    public void setCompanyNature(CompanyNature companyNature) {
        this.companyNature = companyNature;
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

    public AddressMode getAddressMode() {
        return addressMode;
    }

    public void setAddressMode(AddressMode addressMode) {
        this.addressMode = addressMode;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public Date getRegistDateEnd() {
        return registDateEnd;
    }

    public void setRegistDateEnd(Date registDateEnd) {
        this.registDateEnd = registDateEnd;
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

    public Boolean getHasBankAccount() {
        return hasBankAccount;
    }

    public void setHasBankAccount(Boolean hasBankAccount) {
        this.hasBankAccount = hasBankAccount;
    }

    public Boolean getTaxRegister() {
        return taxRegister;
    }

    public void setTaxRegister(Boolean taxRegister) {
        this.taxRegister = taxRegister;
    }

    public List<DemandQualificationDetail> getQualificationDetails() {
        return qualificationDetails;
    }

    public void setQualificationDetails(List<DemandQualificationDetail> qualificationDetails) {
        this.qualificationDetails = qualificationDetails;
    }

    public List<DemandIntangibleAssets> getIntangibleAssetss() {
        return intangibleAssetss;
    }

    public void setIntangibleAssetss(List<DemandIntangibleAssets> intangibleAssetss) {
        this.intangibleAssetss = intangibleAssetss;
    }

    public List<DemandFixedAssets> getFixedAssetss() {
        return fixedAssetss;
    }

    public void setFixedAssetss(List<DemandFixedAssets> fixedAssetss) {
        this.fixedAssetss = fixedAssetss;
    }

    public String getLicenseAdvantage() {
        return licenseAdvantage;
    }

    public void setLicenseAdvantage(String licenseAdvantage) {
        this.licenseAdvantage = licenseAdvantage;
    }

    public SelingStatus getSelingStatus() {
        return selingStatus;
    }

    public void setSelingStatus(SelingStatus selingStatus) {
        this.selingStatus = selingStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getCompanyFeatureStr() {
        return companyFeatureStr;
    }

    public void setCompanyFeatureStr(String companyFeatureStr) {
        this.companyFeatureStr = companyFeatureStr;
    }

    public String getCompanyNatureStr() {
        return companyNatureStr;
    }

    public void setCompanyNatureStr(String companyNatureStr) {
        this.companyNatureStr = companyNatureStr;
    }

    public String getProvinceStr() {
        return provinceStr;
    }

    public void setProvinceStr(String provinceStr) {
        this.provinceStr = provinceStr;
    }

    public String getRegistDateStr() {
        return registDateStr;
    }

    public void setRegistDateStr(String registDateStr) {
        this.registDateStr = registDateStr;
    }

    public String getCityStr() {
        return cityStr;
    }

    public void setCityStr(String cityStr) {
        this.cityStr = cityStr;
    }
}
