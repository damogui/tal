package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_order_prod_item",header="订单项价格")
public class OrderProdItem extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 6055087505229323231L;
	@Column(name="order_prod_id",header="订单项序号")
    private Integer orderProdId;

    @Column(name="price_id",header="定价序号")
    private Integer priceId;
    
    @Column(name="unit_name",header="单位名称")
    private String unitName;
    
    @Column(name="service_name",header="服务名称")
    private String serviceName;
    
    @Column(name="quantity",header="数量")
    private Integer quantity;
    
    @Column(name="price",header="单价")
    private Integer price;
    
    @Column(name="price_original",header="原价")
    private Integer priceOriginal;
    
    @Column(name="price_refund",header="已退款价")
    private Integer priceRefund;
    
    @Column(name="is_bbk",header="是否是八百客的数据")
    private String isBbk="0";

    public Integer getOrderProdId() {
        return orderProdId;
    }
    public void setOrderProdId(Integer orderProdId) {
        this.orderProdId = orderProdId;
    }
    public Integer getPriceId() {
        return priceId;
    }
    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }
    public String getUnitName() {
        return unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getPriceOriginal() {
        return priceOriginal;
    }
    public void setPriceOriginal(Integer priceOriginal) {
        this.priceOriginal = priceOriginal;
    }
    public Integer getPriceRefund() {
        return priceRefund;
    }
    public void setPriceRefund(Integer priceRefund) {
        this.priceRefund = priceRefund;
    }
    public String getIsBbk() {
        return isBbk;
    }
    public void setIsBbk(String isBbk) {
        this.isBbk = isBbk;
    }
}