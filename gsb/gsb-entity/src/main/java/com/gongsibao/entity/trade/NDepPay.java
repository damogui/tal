package com.gongsibao.entity.trade;

import com.gongsibao.entity.supplier.Supplier;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

/**
 * Created by win on 2018/2/26.
 */
@Table(name = "n_dep_pay", header = "部门支付表")
public class NDepPay  extends Entity {
    @Column(name = "amount", header = "支付额")
    private  Integer amount;
    @Column(name = "dep_id", header = "部门Id")
    private  Integer depId;
    @Column(name = "order_pay_map_id", header = "支付明细Id")
    private  Integer orderPayMapId;
    @Column(name = "salesman_id", header = "员工Id")
    private  Integer salesmanId;

    /*new beg*/

    @Reference(foreignKey = "orderPayMapId", header = "支付明细")
    private OrderPayMap orderPayMap;

    /*new end*/
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    public Integer getOrderPayMapId() {
        return orderPayMapId;
    }

    public void setOrderPayMapId(Integer orderPayMapId) {
        this.orderPayMapId = orderPayMapId;
    }

    public OrderPayMap getOrderPayMap() {
        return orderPayMap;
    }

    public void setOrderPayMap(OrderPayMap orderPayMap) {
        this.orderPayMap = orderPayMap;
    }
}
