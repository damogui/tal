package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;

@Table(name="prod_price",header="产品定价")
public class Price extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 8221947936352254477L;

	@Column(name="service_id",header="服务序号")
    private Integer serviceId;
	
	@Reference(foreignKey="serviceId",header="服务",primaryKey="pkid")
	private ProductService service;

    @Column(name="city_id",header="地区序号，dict=1")
    private Integer cityId;
    
	@Reference(foreignKey="cityId",header="城市")
	private Dict city;
	
    @Column(name="price_audit_id",header="定价审核序号")
    private Integer priceAuditId;

	@Reference(foreignKey="priceAuditId",header="服务",primaryKey="pkid")
	private PriceAudit priceAudit;

	@Column(name="original_price",header="原价，单位“分”")
    private Integer originalPrice;
    
    @Column(name="platform_price",header="平台内价格,单位分")
    private Integer platformPrice;
    
    @Column(name="price",header="单价，单位“分”")
    private Integer price;
    
    @Column(name="cost",header="成本价，单位“分”")
    private Integer cost = -1;
    
    @Column(name="is_must",header="是否必选")
    private Boolean necessary = false;
    
    @Column(name="stock",header="库存")
    private Integer stock = -1;
    
    @Column(name="is_on_sale",header="是否上架")
    private Boolean onSale = false;
    
    @Column(name="remark",header="说明")
    private String remark;
    
    @Column(name="agent_id",header="sqlserver代理商id")
    private String agentId;
    
    @Column(name="agent_name",header="sqlserver代理商名称")
    private String agentName;

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
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

	public Integer getPriceAuditId() {
		return priceAuditId;
	}

	public void setPriceAuditId(Integer priceAuditId) {
		this.priceAuditId = priceAuditId;
	}

	public PriceAudit getPriceAudit() {
		return priceAudit;
	}

	public void setPriceAudit(PriceAudit priceAudit) {
		this.priceAudit = priceAudit;
	}

	public Integer getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Integer originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Integer getPlatformPrice() {
		return platformPrice;
	}

	public void setPlatformPrice(Integer platformPrice) {
		this.platformPrice = platformPrice;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Boolean getNecessary() {
		return necessary;
	}

	public void setNecessary(Boolean necessary) {
		this.necessary = necessary;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Boolean getOnSale() {
		return onSale;
	}

	public void setOnSale(Boolean onSale) {
		this.onSale = onSale;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public ProductService getService() {
		return service;
	}

	public void setService(ProductService service) {
		this.service = service;
	}
}