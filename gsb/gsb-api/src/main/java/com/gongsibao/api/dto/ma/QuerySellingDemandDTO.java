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
 * 出售信息查询的query类
 */
public class QuerySellingDemandDTO {


    //公司名称
    private String companyName;

    //类金融牌照
    private String bankType;
    //企业资质类型 EnterpriseQualification  enterpriseQualification
    private int comQualType;

    //省
    private int provinceId;
    //市
    private int cityId;
    //县
    private int countyId;
    //是否精选
    private int isGood;
    //是否热销
    private int isHot;
    //公司类型
    private int companyType;
    //公司性质
    private int companyNature;
    //年限类型
    private int yearType;
    //页索引
    private int pageIndex;
    //页大小
    private int pageSize;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getComQualType() {
        return comQualType;
    }

    public void setComQualType(int comQualType) {
        this.comQualType = comQualType;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public int getIsGood() {
        return isGood;
    }

    public void setIsGood(int isGood) {
        this.isGood = isGood;
    }

    public int getIsHot() {
        return isHot;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }

    public int getCompanyType() {
        return companyType;
    }

    public void setCompanyType(int companyType) {
        this.companyType = companyType;
    }

    public int getCompanyNature() {
        return companyNature;
    }

    public void setCompanyNature(int companyNature) {
        this.companyNature = companyNature;
    }

    public int getYearType() {
        return yearType;
    }

    public void setYearType(int yearType) {
        this.yearType = yearType;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }
}
