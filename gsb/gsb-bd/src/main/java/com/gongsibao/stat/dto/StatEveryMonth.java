package com.gongsibao.stat.dto;


import com.gongsibao.utils.AmountUtils;

import java.io.Serializable;


public class StatEveryMonth implements Serializable {
    private static final long serialVersionUID = -8756589634018062278L;

    private Integer month;

    private Double amount;

    private Integer type;

    private Integer price;

    private Integer orderId;

    private String timeNode;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getAmount() {
        return AmountUtils.round(amount, 2);
    }

    public void setAmount(Integer amount) {
        this.amount = AmountUtils.div(amount, 100 * 10000, 6);
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getTimeNode() {
        return timeNode;
    }

    public void setTimeNode(String timeNode) {
        this.timeNode = timeNode;
    }
}
