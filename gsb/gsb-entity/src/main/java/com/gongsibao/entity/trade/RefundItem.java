package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_refund_item")
public class RefundItem extends BaseEntity {

	private static final long serialVersionUID = -3553767117969884658L;
	
	@Column(name="refund_id",header="RefundId")
    private Integer refundId;
    @Column(name="order_id",header="OrderId")
    private Integer orderId;
    @Column(name="order_prod_id",header="OrderProdId")
    private Integer orderProdId;
    @Column(header="amount")
    private Integer amount;
    @Column(header="cost")
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