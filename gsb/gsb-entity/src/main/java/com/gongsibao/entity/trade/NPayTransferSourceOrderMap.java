package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.entity.Entity;

/**
 * Created by win on 2018/2/26.
 */
public class NPayTransferSourceOrderMap extends Entity {


    @Column(name = "pay_id", header = "支付Id")
    private   Integer  payId;
    @Column(name = "transfer_source_order_id", header = "转移来源订单id")
    private   Integer  transferSourceOrderId;


    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public Integer getTransferSourceOrderId() {
        return transferSourceOrderId;
    }

    public void setTransferSourceOrderId(Integer transferSourceOrderId) {
        this.transferSourceOrderId = transferSourceOrderId;
    }
}

