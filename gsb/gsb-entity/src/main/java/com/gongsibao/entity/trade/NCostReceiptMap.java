package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

/**
 * Created by win on 2018/2/26.
 */
@Table(name = "n_costreceipt_map", header = "消费收据关系")
public class NCostReceiptMap  extends Entity {
    @Column(name = "cost_id", header = "消费Id")
    private  Integer costId;
    @Column(name = "cost_receipt", header = "消费数据")
    private  String costReceipt;

    @Column(name = "receipt_id", header = "收据Id")
    private  Integer receiptId;

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public String getCostReceipt() {
        return costReceipt;
    }

    public void setCostReceipt(String costReceipt) {
        this.costReceipt = costReceipt;
    }

    public Integer getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Integer receiptId) {
        this.receiptId = receiptId;
    }
}
