package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_order_pay_map")
public class OrderPayMap extends BaseEntity {
	
	private static final long serialVersionUID = -9139177565018673953L;
	
	@Column(name="order_id",header="订单")
    private Integer orderId;
    @Column(name="pay_id",header="支付")
    private Integer payId;
    
    @Reference(header="支付",foreignKey="payId")
    private Pay pay;

    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getPayId() {
        return payId;
    }
    public void setPayId(Integer payId) {
        this.payId = payId;
    }
}