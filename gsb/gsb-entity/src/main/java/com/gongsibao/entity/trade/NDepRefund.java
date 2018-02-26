package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

/**
 * Created by win on 2018/2/26.
 */
@Table(name = "n_dep_refund", header = "退款")
public class NDepRefund  extends Entity{
    @Column(name = "amount", header = "数量")
    private  Integer amount;
    @Column(name = "dep", header = "误差")
    private  Integer dep;
    @Column(name = "refund_id", header = "退款")
    private  Integer refund_id;

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

    public Integer getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(Integer refund_id) {
        this.refund_id = refund_id;
    }

    public Integer getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Integer salesmanId) {
        this.salesmanId = salesmanId;
    }
}
