package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.entity.Entity;

/**
 * Created by win on 2018/2/26.
 */
public class NOrderTaskMap extends Entity {

    @Column(name = "order_id", header = "订单")
    private  Integer orderId;
    @Column(name = "task_id", header = "任务Id")
    private  Integer taskId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}
