package com.gongsibao.entity.crm;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.product.Product;

@Table(name="crm_customer_prod_map",header="意向产品")
public class CustomerProdMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 9143414453489406854L;
	@Column(name="customer_id")
    private Integer customerId;
	
	@JsonIgnore
    @Reference(foreignKey="customerId",primaryKey="pkid")
    private Customer customer;
    
    @Column(name="product_id")
    private Integer productId;
    
    @Reference(foreignKey="productId",header="产品",primaryKey="pkid")
    private Product product;
    
    @Column(name="city_id")
    private Integer cityId = 0;
    
	@Column(name="d_province_id")
	private Integer dProvinceId;
	
	@Reference(foreignKey="dProvinceId",header="省份",primaryKey="pkid")
	private Dict dProvince;
	
	@Column(name="d_city_id")
	private Integer dCityId;
	
	@Reference(foreignKey="dCityId",header="城市",primaryKey="pkid")
	private Dict dCity;
	
	@Column(name="d_county_id")
	private Integer dCountyId;
	
	@Reference(foreignKey="dCountyId",header="区/县",primaryKey="pkid")
	private Dict dCounty;

    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public Integer getCityId() {
        return cityId;
    }
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}