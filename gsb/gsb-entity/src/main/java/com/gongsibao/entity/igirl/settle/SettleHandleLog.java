package com.gongsibao.entity.igirl.settle;

import com.gongsibao.entity.igirl.settle.dict.SettleHandleStatus;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ig_order_settle_handle_log", header = "结算单处理日志")
public class SettleHandleLog extends Entity {

    @Column(name = "settle_id", header = "结算单id")
    private Integer settleId;

    @Column(name = "after_status", size = 1, header = "操作后状态")
    private SettleHandleStatus  afterStatus;

    @Column(name = "previous_status", size = 1, header = "操作前状态")
    private SettleHandleStatus previousStatus;

    @Column(name = "memo", header = "操作说明")
    private String memo;

    public Integer getSettleId() {
        return settleId;
    }

    public void setSettleId(Integer settleId) {
        this.settleId = settleId;
    }

    public SettleHandleStatus getAfterStatus() {
        return afterStatus;
    }

    public void setAfterStatus(SettleHandleStatus afterStatus) {
        this.afterStatus = afterStatus;
    }

    public SettleHandleStatus getPreviousStatus() {
        return previousStatus;
    }

    public void setPreviousStatus(SettleHandleStatus previousStatus) {
        this.previousStatus = previousStatus;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
