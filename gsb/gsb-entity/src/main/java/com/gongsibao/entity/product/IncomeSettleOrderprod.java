package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.trade.OrderProd;

@Table(name="prod_income_settle_orderprod",header="结算打款与订单关联表")
public class IncomeSettleOrderprod extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1989806471686312737L;
	@Column(name="settle_id",header="结算打款id")
    private Integer settleId;
	
	@Reference(foreignKey="settleId",header="结算打款")
	private IncomeSettle settle;
	
    @Column(name="order_prod_id",header="明细订单号")
    private Integer orderProdId;
    
	@Reference(foreignKey="orderProdId",header="明细订单号")
	private OrderProd orderProd;
    
    @Column(name="price",header="结算金额")
    private Integer price;
    
    @Column(name="remainder_price",header="剩余未结算金额")
    private Integer remainderPrice;

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
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getRemainderPrice() {
        return remainderPrice;
    }
    public void setRemainderPrice(Integer remainderPrice) {
        this.remainderPrice = remainderPrice;
    }
	public IncomeSettle getSettle() {
		return settle;
	}
	public void setSettle(IncomeSettle settle) {
		this.settle = settle;
	}
	public OrderProd getOrderProd() {
		return orderProd;
	}
	public void setOrderProd(OrderProd orderProd) {
		this.orderProd = orderProd;
	}
}