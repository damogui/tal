package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_refund_item",header="退单项")
public class RefundItem extends BaseEntity {

	private static final long serialVersionUID = -3553767117969884658L;

	@Column(name="refund_id",header="退款序号")
    private Integer refundId;
	
    @Column(name="order_id",header="支付订单序号")
    private Integer orderId;
    
    @Column(name="order_prod_id",header="产品订单序号")
    private Integer orderProdId;
    
    @Column(name="amount",header="退款金额")
    private Integer amount;
    
    @Column(name="cost",header="成本")
    private Integer cost;

    public Integer getRefundId() {
        return refundId;
    }
    public void setRefundId(Integer refundId) {
        this.refundId = refundId;
    }
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getOrderProdId() {
        return orderProdId;
    }
    public void setOrderProdId(Integer orderProdId) {
        this.orderProdId = orderProdId;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public Integer getCost() {
        return cost;
    }
    public void setCost(Integer cost) {
        this.cost = cost;
    }
}