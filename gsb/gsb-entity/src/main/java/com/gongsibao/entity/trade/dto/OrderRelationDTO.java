package com.gongsibao.entity.trade.dto;

import com.gongsibao.entity.trade.NDepPay;

import java.util.List;

/**
 * Created by win on 2018/3/14.
 */
/*创建回款业绩使用*/
public class OrderRelationDTO{

    private  Integer orderId;
    private  Integer orderCutAmount;
    private  Integer payType;
    private List<NDepPay> items;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderCutAmount() {
        return orderCutAmount;
    }

    public void setOrderCutAmount(Integer orderCutAmount) {
        this.orderCutAmount = orderCutAmount;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public List<NDepPay> getItems() {
        return items;
    }

    public void setItems(List<NDepPay> items) {
        this.items = items;
    }
}