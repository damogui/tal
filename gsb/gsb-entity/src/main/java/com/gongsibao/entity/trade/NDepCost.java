package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

/**
 * Created by win on 2018/2/26.
 */
@Table(name = "n_dep_cost", header = "部门成本")
public class NDepCost extends Entity {
    @Column(name = "amount", header = "成本金额")
    private  Integer amount;
    @Column(name = "dep_id", header = "部门Id")
    private  Integer depId;
    @Column(name = "order_cost_map_id", header = "订单成本关系Id")
    private  Integer orderCostMapId;
    @Column(name = "salesman_id", header = "员工Id")
    private  Integer salesmanId;


    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public Integer getOrderCostMapId() {
        return orderCostMapId;
    }

    public void setOrderCostMapId(Integer orderCostMapId) {
        this.orderCostMapId = orderCostMapId;
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
