package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

/**
 * Created by win on 2018/2/26.
 */
@Table(name = "n_order_prod_coupon", header = "订单产品优惠券")
public class NOrderProdCoupon  extends Entity {
    @Column(name = "coupon_id", header = "优惠券Id")
    private  Integer couponId;
    @Column(name = "coupon_price", header = "优惠券面值")
    private  Integer couponPrice;
    @Column(name = "order_id", header = "订单Id")
    private  Integer orderId;


    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(Integer couponPrice) {
        this.couponPrice = couponPrice;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
