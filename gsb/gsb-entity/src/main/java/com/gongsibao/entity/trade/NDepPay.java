package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
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
    @Column(name = "order_pay_map_id", header = "订单支付关系Id")
    private  Integer order_pay_map_id;
    @Column(name = "salesman_id", header = "员工Id")
    private  Integer salesmanId;


    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public Integer getOrder_pay_map_id() {
        return order_pay_map_id;
    }

    public void setOrder_pay_map_id(Integer order_pay_map_id) {
        this.order_pay_map_id = order_pay_map_id;
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
