package com.gongsibao.entity.trade.settle;

import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.SoOrder;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import java.math.BigDecimal;

@Table(name = "so_settle_order", header = "结算单关联订单")
public class SettleOrder extends Entity {

    @Column(name = "settle_id", header = "结算单id")
    private Integer settleId;

    @Column(name = "order_prod_id", header = "明细订单id")
    private Integer orderProdId;

    @Reference(foreignKey = "orderProdId", primaryKey = "pkid", header = "明细订单")
    private OrderProd orderProd;

    @Column(name = "order_id", header = "订单id")
    private Integer orderId;

    @Reference(foreignKey = "orderId", primaryKey = "pkid", header = "订单")
    private SoOrder soOrder;

    @Column(name = "cost", size = 10, precition = 2, header = "冗余字段-成本")
    private BigDecimal cost;

    @Column(name = "charge", size = 10, precition = 2, header = "冗余字段-服务费")
    private BigDecimal charge;

    @Column(name = "commission", size = 10, precition = 2, header = "佣金")
    private BigDecimal commission;

    public Integer getSettleId() {
        return settleId;
    }

    public void setSettleId(Integer settleId) {
        this.settleId = settleId;
    }

    public Integer getOrderProdId() {
        return orderProdId;
    }

    public void setOrderProdId(Integer orderProdId) {
        this.orderProdId = orderProdId;
    }

    public OrderProd getOrderProd() {
        return orderProd;
    }

    public void setOrderProd(OrderProd orderProd) {
        this.orderProd = orderProd;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public SoOrder getSoOrder() {
        return soOrder;
    }

    public void setSoOrder(SoOrder soOrder) {
        this.soOrder = soOrder;
    }

    public BigDecimal getCost() {
        return null == cost ? BigDecimal.ZERO : cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getCharge() {
        return null == charge ? BigDecimal.ZERO : charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }
}
