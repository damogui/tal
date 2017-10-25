package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_refund_item_price")
public class RefundItemPrice extends BaseEntity {

	private static final long serialVersionUID = -3421520323370993973L;
	
	@Column(name="refund_id",header="RefundId")
    private Integer refundId;
    @Column(name="order_prod_item_id",header="订单产品")
    private Integer orderProdItemId;
    @Column(header="金额")
    private Integer amount;
    @Column(header="成本")
    private Integer cost;
    @Column(name="so_refund_item_id",header="订单退款明细")
    private Integer soRefundItemId;

    public Integer getRefundId() {
        return refundId;
    }
    public void setRefundId(Integer refundId) {
        this.refundId = refundId;
    }
    public Integer getOrderProdItemId() {
        return orderProdItemId;
    }
    public void setOrderProdItemId(Integer orderProdItemId) {
        this.orderProdItemId = orderProdItemId;
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
    public Integer getSoRefundItemId() {
        return soRefundItemId;
    }
    public void setSoRefundItemId(Integer soRefundItemId) {
        this.soRefundItemId = soRefundItemId;
    }
}