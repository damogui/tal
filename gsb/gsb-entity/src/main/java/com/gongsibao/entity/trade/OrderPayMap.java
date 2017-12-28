package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_order_pay_map",header="订单和支付关系")
public class OrderPayMap extends BaseEntity {
	
	private static final long serialVersionUID = -9139177565018673953L;
	
	@Column(name="order_id",header="订单序号")
    private Integer orderId;
	
    @Reference(header="订单",foreignKey="orderId")
    private SoOrder soOrder;
	
    @Column(name="pay_id",header="支付序号")
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
	public SoOrder getSoOrder() {
		return soOrder;
	}
	public void setSoOrder(SoOrder soOrder) {
		this.soOrder = soOrder;
	}
	public Pay getPay() {
		return pay;
	}
	public void setPay(Pay pay) {
		this.pay = pay;
	}
}
