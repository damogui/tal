package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.entity.Entity;

/**
 * Created by win on 2018/2/26.
 */
public class NRefundTransferTargetOrderMap extends Entity {

    @Column(name = "refund_id", header = "退款")
    private Integer refundId;
    @Column(name = "transfer_target_order_id", header = "转移目标订单")
    private  Integer transferTargetOrderId;

    public Integer getRefundId() {
        return refundId;
    }

    public void setRefundId(Integer refundId) {
        this.refundId = refundId;
    }

    public Integer getTransferTargetOrderId() {
        return transferTargetOrderId;
    }

    public void setTransferTargetOrderId(Integer transferTargetOrderId) {
        this.transferTargetOrderId = transferTargetOrderId;
    }
}
