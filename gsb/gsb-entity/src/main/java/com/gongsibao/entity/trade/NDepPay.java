package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

/**
 * Created by win on 2018/2/26.
 */
@Table(name = "n_dep_pay", header = "偏差支付")
public class NDepPay  extends Entity {
    @Column(name = "amount", header = "数量")
    private  Integer amount;
    @Column(name = "dep", header = "偏差")
    private  Integer dep;
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

    public Integer getDep() {
        return dep;
    }

    public void setDep(Integer dep) {
        this.dep = dep;
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
}
