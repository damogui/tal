package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

/**
 * Created by win on 2018/2/26.
 */
@Table(name = "n_order_cost", header = "订单花费")
public class NOrderCost   extends Entity{
    @Column(name = "amount", header = "数量")
    private  Integer amount;
    @Column(name = "cost_prod_type", header = "花费产品类型")
    private  Integer costProdType;
    @Column(name = "cost_type", header = "花费类型")
    private  Integer costType;
    @Column(name = "set_book", header = "设定")
    private  Integer setBook;
    @Column(name = "supplier_id", header = "供应商Id")
    private  Integer supplierId;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getCostProdType() {
        return costProdType;
    }

    public void setCostProdType(Integer costProdType) {
        this.costProdType = costProdType;
    }

    public Integer getCostType() {
        return costType;
    }

    public void setCostType(Integer costType) {
        this.costType = costType;
    }

    public Integer getSetBook() {
        return setBook;
    }

    public void setSetBook(Integer setBook) {
        this.setBook = setBook;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
}
