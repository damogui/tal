package com.gongsibao.entity.product;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="prod_price")
public class Price extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 8221947936352254477L;
	@Column(name="service_id")
    private Integer serviceId;
    @Column(name="city_id")
    private Integer cityId;
    @Column(name="price_audit_id")
    private Integer priceAuditId;
    @Column(name="original_price")
    private Integer originalPrice;
    private Integer price;
    private Integer cost;
    @Column(name="is_must")
    private Integer isMust;
    private Integer stock;
    @Column(name="is_on_sale")
    private Integer isOnSale;
    @Column(name="add_time")
    private Date addTime;
    @Column(name="add_user_id")
    private Integer addUserId;
    private String remark;
    @Column(name="agent_id")
    private String agentId;
    @Column(name="agent_name")
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
    public Integer getPriceAuditId() {
        return priceAuditId;
    }
    public void setPriceAuditId(Integer priceAuditId) {
        this.priceAuditId = priceAuditId;
    }
    public Integer getOriginalPrice() {
        return originalPrice;
    }
    public void setOriginalPrice(Integer originalPrice) {
        this.originalPrice = originalPrice;
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
    public Integer getIsMust() {
        return isMust;
    }
    public void setIsMust(Integer isMust) {
        this.isMust = isMust;
    }
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public Integer getIsOnSale() {
        return isOnSale;
    }
    public void setIsOnSale(Integer isOnSale) {
        this.isOnSale = isOnSale;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
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
}