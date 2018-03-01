package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

/**
 * Created by win on 2018/2/26.
 */
@Table(name = "n_dep_receivable", header = "部门应收核算款")
public class NDepReceivable  extends Entity {
    @Column(name = "amount", header = "应收款")
    private  Integer amount;
    @Column(name = "dep_id", header = "部门Id")
    private  Integer depId;
    @Column(name = "order_id", header = "订单Id")
    private  Integer orderId;
    @Column(name = "salesman_id", header = "员工Id")
    private  Integer salesmanId;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Integer salesmanId) {
        this.salesmanId = salesmanId;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }
}
