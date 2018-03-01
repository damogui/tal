package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

/**
 * Created by win on 2018/2/26.
 */
@Table(name = "n_marketing_cost", header = "市场花费")
public class NMarketingCost  extends Entity {
    @Column(name = "bid_price", header = "出价")
    private  Integer bidPrice;
    @Column(name = "dep_id", header = "部门Id")
    private  Integer depId;
    @Column(name = "source_link", header = "来源连接")
    private  String sourceLink;
    @Column(name = "supplier_id", header = "供应商Id")
    private  Integer supplierId;

    public Integer getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Integer bidPrice) {
        this.bidPrice = bidPrice;
    }



    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }
}
