package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;

@Table(name="prod_package_price_map",header="城市定价关联表")
public class PackagePriceMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6292565921773409119L;
	@Column(name="package_id",header="套餐id")
    private Integer packageId;
	
	@Reference(foreignKey="packageId",header="套餐")
	private ProductPackage productPackage;
	
    @Column(name="product_id",header="套餐产品关联表id")
    private Integer productId;
    
	@Reference(foreignKey="productId",header="城市")
	private Product product;
    
    @Column(name="sort",header="排序")
    private Integer sort;

    @Column(name="city_id",header="城市id")
    private Integer cityId;
    
	@Reference(foreignKey="cityId",header="城市")
	private Dict city;
    
    @Column(name="service_id",header="服务id")
    private Integer serviceId;
    
	@Reference(foreignKey="serviceId",header="服务")
	private ProductService service;
    
    @Column(name="price_id",header="定价id")
    private Integer priceId;
    
	@Reference(foreignKey="priceId",header="定价")
	private Price price;
    
    @Column(name="price",header="套餐价格")
    private Integer priceAmount;
    
    @Column(name="is_must",header="是否必选 0否 1是")
    private Boolean necessary = false;
    
    @Column(name="is_enabled",header="是否必选 0否 1是")
    private Boolean enabled = false;
    
    public Integer getPackageId() {
        return packageId;
    }
    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }
	public ProductPackage getProductPackage() {
		return productPackage;
	}
	public void setProductPackage(ProductPackage productPackage) {
		this.productPackage = productPackage;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
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
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public ProductService getService() {
		return service;
	}
	public void setService(ProductService service) {
		this.service = service;
	}
	public Integer getPriceId() {
		return priceId;
	}
	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}
	public Integer getPriceAmount() {
		return priceAmount;
	}
	public void setPriceAmount(Integer priceAmount) {
		this.priceAmount = priceAmount;
	}
	public Boolean getNecessary() {
		return necessary;
	}
	public void setNecessary(Boolean necessary) {
		this.necessary = necessary;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
    
}