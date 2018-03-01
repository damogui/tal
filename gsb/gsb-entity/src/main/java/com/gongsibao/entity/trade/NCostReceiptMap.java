package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

/**
 * Created by win on 2018/2/26.
 */
@Table(name = "n_costreceipt_map", header = "成本接受关系表")
public class NCostReceiptMap  extends Entity {
    @Column(name = "cost_id", header = "成本Id")
    private  Integer costId;
    @Column(name = "cost_receipt", header = "成本接受")
    private  Integer costReceipt;

    @Column(name = "receipt_id", header = "接收人Id")
    private  Integer receiptId;

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }



    public Integer getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Integer receiptId) {
        this.receiptId = receiptId;
    }

    public Integer getCostReceipt() {
        return costReceipt;
    }

    public void setCostReceipt(Integer costReceipt) {
        this.costReceipt = costReceipt;
    }
}
