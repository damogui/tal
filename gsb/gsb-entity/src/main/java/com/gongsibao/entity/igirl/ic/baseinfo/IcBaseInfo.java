package com.gongsibao.entity.igirl.ic.baseinfo;

import com.gongsibao.entity.igirl.ic.dict.CorpBjZone;
import com.gongsibao.entity.igirl.ic.dict.CorpPropertyRight;
import com.gongsibao.entity.igirl.ic.dict.CorpResidenceProvision;
import com.gongsibao.entity.igirl.ic.dict.CorpSpecial;
import com.gongsibao.entity.igirl.ic.ex.dict.BooleanType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ic_base_info",header = "工商注册基础信息")
public class IcBaseInfo extends Entity{
    @Column(name = "reg_cap",header = "注册资本")
    private String regCap;

    @Column(name = "trade_term",header = "营业期限")
    private String tradeTerm;

    @Column(name = "spec_industry",header = "是否属于特殊行业")
    private CorpSpecial specIndustry;

    @Column(name = "dom_district",header = "住所（经营场所）")
    private CorpBjZone domDistrict;

    @Column(name = "dom_detail",header = "住所详细信息")
    private String domDetail;

    @Column(name = "inpark",header = "是否位于中关村国家自主创新示范园区及“三城一区”内")
    private String inpark;

    @Column(name = "pro_loc_city",header = "生产经营地")
    private CorpBjZone  proLocCity;

    @Column(name = "pro_loc_other",header = "生产经营地详细信息")
    private String proLocOther;

    @Column(name = "dom_owner",header = "住所（产权人）")
    private String domOwner;

    @Column(name = "dom_own_type",header = "住所产权类型")
    private CorpPropertyRight domOwnType;

    @Column(name = "dom_pro_right",header = "住所提供方式")
    private CorpResidenceProvision domProRight;

    @Column(name = "dom_term",header = "住所使用期限")
    private Integer domTerm;

    @Column(name = "spec_area",header = "住所是否在以下地区")
    private String specArea;

    @Column(name = "dom_acreage",header = "营业面积")
    private Double domAcreage;

    @Column(name = "dom_usage_type",header = "房屋用途")
    private String domUsageType;

    @Column(name = "sgzw_flag",header = "是否市级国有资产监督管理机构履行出资人职责的公司以及该公司设立的控股50%以上的公司")
    private String sgzwFlag;

    @Column(name = "copy_no",header = "执照副本数")
    private String copyNo;

    @Column(name = "custom_scope",header = "经营范围")
    private String customScope;

    @Column(name = "executive",header = "是否设立董事会")
    private BooleanType executive = BooleanType.NO;

    //supervisorNotSet:不设立监事会,监事人数1-2人     supervisorYesSet:设立监事会,监事人数至少为3人,其中设监事会主席1人
    @Column(name = "supervisor",header = "是否设立监事会")
    private BooleanType supervisor = BooleanType.NO;

    public String getRegCap() {
        return regCap;
    }

    public void setRegCap(String regCap) {
        this.regCap = regCap;
    }

    public String getTradeTerm() {
        return tradeTerm;
    }

    public void setTradeTerm(String tradeTerm) {
        this.tradeTerm = tradeTerm;
    }

    public CorpSpecial getSpecIndustry() {
        return specIndustry;
    }

    public void setSpecIndustry(CorpSpecial specIndustry) {
        this.specIndustry = specIndustry;
    }

    public CorpBjZone getDomDistrict() {
        return domDistrict;
    }

    public void setDomDistrict(CorpBjZone domDistrict) {
        this.domDistrict = domDistrict;
    }

    public String getDomDetail() {
        return domDetail;
    }

    public void setDomDetail(String domDetail) {
        this.domDetail = domDetail;
    }

    public String getInpark() {
        return inpark;
    }

    public void setInpark(String inpark) {
        this.inpark = inpark;
    }

    public CorpBjZone getProLocCity() {
        return proLocCity;
    }

    public void setProLocCity(CorpBjZone proLocCity) {
        this.proLocCity = proLocCity;
    }

    public String getProLocOther() {
        return proLocOther;
    }

    public void setProLocOther(String proLocOther) {
        this.proLocOther = proLocOther;
    }

    public String getDomOwner() {
        return domOwner;
    }

    public void setDomOwner(String domOwner) {
        this.domOwner = domOwner;
    }

    public CorpPropertyRight getDomOwnType() {
        return domOwnType;
    }

    public void setDomOwnType(CorpPropertyRight domOwnType) {
        this.domOwnType = domOwnType;
    }

    public CorpResidenceProvision getDomProRight() {
        return domProRight;
    }

    public void setDomProRight(CorpResidenceProvision domProRight) {
        this.domProRight = domProRight;
    }

    public Integer getDomTerm() {
        return domTerm;
    }

    public void setDomTerm(Integer domTerm) {
        this.domTerm = domTerm;
    }

    public String getSpecArea() {
        return specArea;
    }

    public void setSpecArea(String specArea) {
        this.specArea = specArea;
    }

    public Double getDomAcreage() {
        return domAcreage;
    }

    public void setDomAcreage(Double domAcreage) {
        this.domAcreage = domAcreage;
    }

    public String getDomUsageType() {
        return domUsageType;
    }

    public void setDomUsageType(String domUsageType) {
        this.domUsageType = domUsageType;
    }

    public String getSgzwFlag() {
        return sgzwFlag;
    }

    public void setSgzwFlag(String sgzwFlag) {
        this.sgzwFlag = sgzwFlag;
    }

    public String getCopyNo() {
        return copyNo;
    }

    public void setCopyNo(String copyNo) {
        this.copyNo = copyNo;
    }

    public String getCustomScope() {
        return customScope;
    }

    public void setCustomScope(String customScope) {
        this.customScope = customScope;
    }
}
