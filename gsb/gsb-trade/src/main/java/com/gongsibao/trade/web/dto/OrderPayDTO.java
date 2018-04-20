package com.gongsibao.trade.web.dto;

import java.io.Serializable;

/**
 * ClassName: PayDTO
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: 处理订单支付传参
 * @date 2018/4/20 14:13
 */
public class OrderPayDTO implements Serializable {
    private static final long serialVersionUID = -2737478213464602660L;

    private Integer orderId;
    private Integer payId;
    private Integer totalFee;
    private String onlineTradeNo;
    private boolean isSuccess;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getOnlineTradeNo() {
        return onlineTradeNo;
    }

    public void setOnlineTradeNo(String onlineTradeNo) {
        this.onlineTradeNo = onlineTradeNo;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
