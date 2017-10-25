package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="prod_income_settle_orderprod")
public class IncomeSettleOrderprod extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1989806471686312737L;
	@Column(name="settle_id")
    private Integer settleId;
    @Column(name="order_prod_id")
    private Integer orderProdId;
    private Integer price;
    @Column(name="remainder_price")
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
}