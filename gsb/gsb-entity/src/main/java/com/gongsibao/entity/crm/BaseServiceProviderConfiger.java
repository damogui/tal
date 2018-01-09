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
	
	@Column(name="service_file_id")
    private Integer serviceFileId;
	
	@JsonIgnore
    @Reference(foreignKey="serviceFileId")
    private BaseServiceProvider serviceFile;
    
    @Column(name="product_id")
    private Integer productId;
    
    @Reference(foreignKey="productId",header="产品")
    private Product product;
    
	@Column(name="province_id")
	private Integer provinceId;
	
	@Reference(foreignKey="provinceId",header="省份")
	private Dict pDict;
	
	@Column(name="city_id")
	private Integer cityId;
	
	@Reference(foreignKey="cityId",header="城市")
	private Dict cDict;
	
	@Column(name="county_id")
	private Integer countyId;
	
	@Reference(foreignKey="county_id",header="区/县")
	private Dict countyDict;

	
	
	public Integer getServiceFileId() {
		return serviceFileId;
	}

	public void setServiceFileId(Integer serviceFileId) {
		this.serviceFileId = serviceFileId;
	}

	public BaseServiceProvider getServiceFile() {
		return serviceFile;
	}

	public void setServiceFile(BaseServiceProvider serviceFile) {
		this.serviceFile = serviceFile;
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

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Dict getpDict() {
		return pDict;
	}

	public void setpDict(Dict pDict) {
		this.pDict = pDict;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Dict getcDict() {
		return cDict;
	}

	public void setcDict(Dict cDict) {
		this.cDict = cDict;
	}

	public Integer getCountyId() {
		return countyId;
	}

	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}

	public Dict getCountyDict() {
		return countyDict;
	}

	public void setCountyDict(Dict countyDict) {
		this.countyDict = countyDict;
	}	
}
