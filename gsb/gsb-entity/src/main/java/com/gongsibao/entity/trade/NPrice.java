package com.gongsibao.entity.trade;

import com.gongsibao.entity.bd.Dict;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

/**
 * Created by win on 2018/2/26.
 */

@Table(name = "n_price", header = "价格表")
public class NPrice extends Entity {
    @Column(name = "address_fee", header = "收信人地址")
    private  String addressFee;
    @Column(name = "amount", header = "数量")
    private  Integer amount;



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
    private Dict county;//district
    @Column(name = "taxpayer_type", header = "纳税人类型")
    private  Integer taxpayerType;
    @Column(name = "version", header = "版本")
    private  Integer version;


    public String getAddressFee() {
        return addressFee;
    }

    public void setAddressFee(String addressFee) {
        this.addressFee = addressFee;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    public Integer getTaxpayerType() {
        return taxpayerType;
    }

    public void setTaxpayerType(Integer taxpayerType) {
        this.taxpayerType = taxpayerType;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
