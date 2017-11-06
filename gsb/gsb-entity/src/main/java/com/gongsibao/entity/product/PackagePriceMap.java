package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="prod_package_price_map")
public class PackagePriceMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1437951551249300914L;
	@Column(name="package_id")
    private Integer packageId;
    @Column(name="package_product_id")
    private Integer packageProductId;
    @Column(name="city_id")
    private Integer cityId;
    @Column(name="service_id")
    private Integer serviceId;
    @Column(name="price_id")
    private Integer priceId;
    private Integer price;
    @Column(name="is_must")
    private Integer isMust;
    @Column(name="is_enabled")
    private Integer isEnabled;

    public Integer getPackageId() {
        return packageId;
    }
    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }
    public Integer getPackageProductId() {
        return packageProductId;
    }
    public void setPackageProductId(Integer packageProductId) {
        this.packageProductId = packageProductId;
    }
    public Integer getCityId() {
        return cityId;
    }
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    public Integer getServiceId() {
        return serviceId;
    }
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
    public Integer getPriceId() {
        return priceId;
    }
    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getIsMust() {
        return isMust;
    }
    public void setIsMust(Integer isMust) {
        this.isMust = isMust;
    }
    public Integer getIsEnabled() {
        return isEnabled;
    }
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }
}