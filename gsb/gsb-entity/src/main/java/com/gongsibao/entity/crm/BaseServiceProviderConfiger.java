package com.gongsibao.entity.crm;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.product.Product;

@Table(name = "base_service_provider_configer", header = "服务商能力配置")
public class BaseServiceProviderConfiger extends BaseEntity{
	
	private static final long serialVersionUID = 9143414453489406854L;
	
	@Column(name="service_provider_id")
    private Integer serviceProviderId;
	
	@JsonIgnore
    @Reference(foreignKey="serviceProviderId")
    private BaseServiceProvider serviceProvider;
    
    @Column(name="product_id")
    private Integer productId;
    
    @Reference(foreignKey="productId",header="产品")
    private Product product;
    
    @Column(name="city_id")
    private Integer cityId = 0;
    
	@Column(name="d_province_id")
	private Integer dProvinceId;
	
	@Reference(foreignKey="dProvinceId",header="省份")
	private Dict dProvince;
	
	@Column(name="d_city_id")
	private Integer dCityId;
	
	@Reference(foreignKey="dCityId",header="城市")
	private Dict dCity;
	
	@Column(name="d_county_id")
	private Integer dCountyId;
	
	@Reference(foreignKey="dCountyId",header="区/县")
	private Dict dCounty;

	
	public Integer getServiceProviderId() {
		return serviceProviderId;
	}

	public void setServiceProviderId(Integer serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

	public BaseServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(BaseServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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

	public Integer getdProvinceId() {
		return dProvinceId;
	}

	public void setdProvinceId(Integer dProvinceId) {
		this.dProvinceId = dProvinceId;
	}

	public Dict getdProvince() {
		return dProvince;
	}

	public void setdProvince(Dict dProvince) {
		this.dProvince = dProvince;
	}

	public Integer getdCityId() {
		return dCityId;
	}

	public void setdCityId(Integer dCityId) {
		this.dCityId = dCityId;
	}

	public Dict getdCity() {
		return dCity;
	}

	public void setdCity(Dict dCity) {
		this.dCity = dCity;
	}

	public Integer getdCountyId() {
		return dCountyId;
	}

	public void setdCountyId(Integer dCountyId) {
		this.dCountyId = dCountyId;
	}

	public Dict getdCounty() {
		return dCounty;
	}

	public void setdCounty(Dict dCounty) {
		this.dCounty = dCounty;
	}
}
