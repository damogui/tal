package com.gongsibao.entity.igirl.res;

import java.io.Serializable;

public class TrademarkCaseResult implements Serializable {

    private static final long serialVersionUID = 3731558225759634095L;

    private int status;

    private String msg;

    private int orderId;

    private int accoutnId;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getAccoutnId() {
        return accoutnId;
    }

    public void setAccoutnId(int accoutnId) {
        this.accoutnId = accoutnId;
    }
}
